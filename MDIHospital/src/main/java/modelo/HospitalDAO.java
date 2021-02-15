/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sergio Cruz
 */
public class HospitalDAO {
    HistoriaClinica objH;

    public HospitalDAO(HistoriaClinica objH) {
        this.objH = objH;
    }
    
    public HospitalDAO() {
        this.objH = new HistoriaClinica();
    }
    
    public DefaultTableModel consultar() //tabla de entrada o salida
    {
        DefaultTableModel plantilla = new DefaultTableModel();
        ConexionBD con = new ConexionBD();//objeto con datos de la base de datos y capaz de conectarse a ella
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();//se crea la instancia para madar peticiones a la bd
            ResultSet datos = consulta.executeQuery("select * from historias_clinicas");//devuelve el resultado a de la consulta a bd
            ResultSetMetaData campos = datos.getMetaData();//devuelve, numeros, tipos y propiedaddes de los objetos en las columnas
            
            for (int i = 1; i <= campos.getColumnCount(); i++) 
            {
                plantilla.addColumn(campos.getColumnName(i));//se añaden las columnas a la tabla
            }
            
            while(datos.next())
            {
                Object fila[] = new Object[campos.getColumnCount()];
                for (int i = 0; i < campos.getColumnCount(); i++) //menor que el número de columnas
                {
                    fila[i] = datos.getObject(i+1);//se guardan los datos de las filas, desde la fila 1, la cero son los nombres de las columnas
                
                }
                plantilla.addRow(fila);
            }
            datos.close();
            con.getConexion().close();
            
        }catch (SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.toString()); 
        }
        return plantilla;        
    }
    
    public String insertar()
    {
        String msj="";
        try
        {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into historias_clinicas values(?,?,?,?,?,?,?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, String.valueOf(objH.getNroHistoria()));
            consulta.setString(2, objH.getFecha().toString());
            consulta.setString(3, objH.getDtsPaciente().getNombre());
            consulta.setString(4, objH.getDtsPaciente().getIdentificacion());
            consulta.setString(5, objH.getDtsPaciente().getDireccion());
            consulta.setString(6, objH.getDtsPaciente().getTelefono());
            consulta.setString(7, String.valueOf(objH.getDtsPaciente().afiliacion()));
            if(objH.getDtsServicio() instanceof Vacunacion)
            {
                consulta.setString(8, "Vacunacion");
                consulta.setDouble(9, objH.valor());
            }
            if(objH.getDtsServicio() instanceof Hospitalizacion)
            {
                consulta.setString(8, "Hospitalizacion");
                consulta.setDouble(9, objH.valorHOPS((Hospitalizacion) objH.getDtsServicio()));
            }
            if(objH.getDtsServicio() instanceof Laboratorios)
            {
                consulta.setString(8, "Laboratorios");
                consulta.setDouble(9, objH.valorLAB((Laboratorios) objH.getDtsServicio()));
            }
            if(objH.getDtsServicio() instanceof CitaMedGenr)
            {
                consulta.setString(8, "Cita Medicina General");
                consulta.setDouble(9, objH.valor());
            }
            
            consulta.execute();
            msj = "Registro exitoso";
            consulta.close();
            conexion.getConexion().close();
        }catch(SQLException e)
        {
            msj = "Error al ingreso de datos" + e.toString();
        }
        return msj;
    }

    public String insertarMedico(String nombre)
    {
        String msj = "";
        
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into medico (nombre) values(?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, nombre);
            consulta.execute();
            msj = "Registro exitoso";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            msj = "Error al ingreso de datos" + ex.toString();
        }
        
        return msj;
    }
    
    public int medico()
    {
        int idmed = 0;
        ConexionBD con = new ConexionBD();//objeto con datos de la base de datos y capaz de conectarse a ella
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();//se crea la instancia para madar peticiones a la bd
            ResultSet datos = consulta.executeQuery("select max(`idmedico`) from `medico`");//devuelve el resultado a de la consulta a bd
            //datos.next();
            JOptionPane.showMessageDialog(null, datos.next());
            int r =  Integer.parseInt(datos.getString(1));
            Random ran = new Random();
            idmed =  1 + ran.nextInt(r);
            consulta.close();
            con.getConexion().close();
            datos.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return idmed;
        
    }
    
    
    //metodo que devuelva un nuemro aleatorio de medico
    
    public String insertar2()
    {
        String msj="";
        try
        {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into paciente values(?,?,?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setInt(1, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
            consulta.setString(2, objH.getDtsPaciente().getNombre());
            consulta.setString(3, objH.getDtsPaciente().getDireccion());
            consulta.setString(4, String.valueOf(objH.getDtsPaciente().afiliacion()));   
            consulta.setString(5, objH.getDtsPaciente().getTelefono());
            consulta.execute();
            
            double valor = 0; 
            String comando2 = "insert into servicio_paciente (id_servicio, id_paciente, nombre_servicio) values(?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando2);
            if(objH.getDtsServicio() instanceof Vacunacion)
            {
                consulta.setInt(1, objH.getDtsServicio().getCodigo());
                consulta.setInt(2, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
                consulta.setString(3, "Vacunacion");
                valor = objH.valor();
                
            }
            if(objH.getDtsServicio() instanceof Hospitalizacion)
            {
                consulta.setInt(1, objH.getDtsServicio().getCodigo());
                consulta.setInt(2, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
                consulta.setString(3, "Hospitalizacion");
                valor =  objH.valorHOPS((Hospitalizacion) objH.getDtsServicio());
            }
            if(objH.getDtsServicio() instanceof Laboratorios)
            {
                consulta.setInt(1, objH.getDtsServicio().getCodigo());
                consulta.setInt(2, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
                consulta.setString(3, "Laboratorios");
                valor = objH.valorLAB((Laboratorios) objH.getDtsServicio());
            }
            if(objH.getDtsServicio() instanceof CitaMedGenr)
            {
                consulta.setInt(1, objH.getDtsServicio().getCodigo());
                consulta.setInt(2, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
                consulta.setString(3, "Cita Medicina General");
                
                
                valor = objH.valor();
            }
            consulta.execute();
            
            
            String comando3 = "insert into historia_clinica (id_medico,id_paciente,fecha,valor,descripcion) values(?,?,?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando3);
            consulta.setInt(1, medico());
            consulta.setInt(2, Integer.parseInt(objH.getDtsPaciente().getIdentificacion()));
            consulta.setString(3, objH.getFecha().toString());
            consulta.setDouble(4, valor);
            consulta.setString(5, objH.getDtsServicio().getDescripcion());
            consulta.execute();
            
            if(objH.getDtsServicio() instanceof Hospitalizacion)
            {
                String comando4 = "insert into hospitalizaciones (id_servicio, fecha_salida) values(?,?)";
                consulta = conexion.getConexion().prepareStatement(comando4);
                consulta.setInt(1, objH.getDtsServicio().getCodigo());
                Hospitalizacion auxH = (Hospitalizacion) objH.getDtsServicio();
                consulta.setString(2, auxH.getSalida().toString());
                consulta.execute();
            }
            
            if(objH.getDtsServicio() instanceof Laboratorios)
            {
                
                Laboratorios auxL = (Laboratorios) objH.getDtsServicio();
                //codigo examen y nombre, el del servicio se repite
                JOptionPane.showMessageDialog(null, String.valueOf(auxL.getExamenes().size()));
                String comando5 = "insert into examenes values (?,?,?,?,?)";
                consulta = conexion.getConexion().prepareStatement(comando5);
                for(int i=0; i<(auxL.getExamenes().size()); i++)
                {
                    
                    consulta.setInt(1,objH.getDtsServicio().getCodigo());
                    consulta.setString(2, auxL.getExamenes().get(i).getCod());
                    consulta.setString(3, auxL.getExamenes().get(i).getNombre());
                    consulta.setString(4, auxL.getExamenes().get(i).getDescripcion());
                    consulta.setDouble(5, auxL.getExamenes().get(i).getValor());
                    consulta.execute();
                }                
            }
            
            msj = "Registro exitoso";
            consulta.close();
            conexion.getConexion().close();
        }catch(MySQLIntegrityConstraintViolationException e)
        {
            msj = "Error al ingreso de datos, llave primaria (DNI) repetida";
        }
        catch(SQLException e)
        {
            msj = "Error al ingreso de datos" + e.toString();
        }
       
        return msj;
    }
    
    public HistoriaClinica getObjH() {
        return objH;
    }

    public void setObjH(HistoriaClinica objH) {
        this.objH = objH;
    }

    @Override
    public String toString() {
        return objH.toString();
    }
    
    
}
