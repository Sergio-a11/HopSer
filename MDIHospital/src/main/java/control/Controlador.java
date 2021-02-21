/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import modelo.Conexion;
import vista.*;

/**
 * Clase que Controla la logica del progrma y los procesos de la vista desde la interfaz de usuario
 * @author dadxc
 */
public class Controlador implements ActionListener, Runnable {
    Registros objR;
    VentanaPrincipal frmPrincipal;
    VentanaRegistrar frmRegistrar;
    VentanaExamenes frmExamenes;
    VentanaConsultar frmConsultar;
    Consultar frmConsultar1;
    ArrayList<Examen> examenes;
    Hospitalizacion auxH;
    Laboratorios auxL;
    Conexion con;
    long total;
    HospitalDAO conexionbd;
    Thread hilo1;
    Hora hora;

    /**
     * Controlador básico, inicialización de las ventanas, variables y actionListener
     * @throws java.io.IOException control de excepciones
     */
    public Controlador() throws IOException {
        this.objR = new Registros();
        this.frmPrincipal = new VentanaPrincipal();
        this.frmRegistrar = new VentanaRegistrar();
        this.frmExamenes = new VentanaExamenes();
        this.frmConsultar = new VentanaConsultar();
        this.frmConsultar1 = new Consultar();
        this.con = new Conexion();
        frmPrincipal.getPndEscritorio().add(frmRegistrar);
        frmPrincipal.getPndEscritorio().add(frmExamenes);
        frmPrincipal.getPndEscritorio().add(frmConsultar);
        frmPrincipal.getPndEscritorio().add(frmConsultar1);
        this.frmPrincipal.getOpcmMedico().addActionListener(this);
        this.frmPrincipal.getOpcmRegistrar().addActionListener(this);
        this.frmPrincipal.getOpcmConsultar().addActionListener(this);
        this.frmPrincipal.getOpcmConsultarHistoria().addActionListener(this);
        this.frmPrincipal.getOpcmSalir().addActionListener(this);
        this.frmRegistrar.getBtnFechaSistema().addActionListener(this);
        this.frmRegistrar.getBtnRegistrar().addActionListener(this);
        this.frmExamenes.getBtnAgregar().addActionListener(this);
        this.frmExamenes.getBtnFinalizar().addActionListener(this);
        this.frmExamenes.getBtnGrupo().add(frmExamenes.getBtnSangre());
        this.frmExamenes.getBtnGrupo().add(frmExamenes.getBtnOrina());
        this.frmExamenes.getBtnGrupo().add(frmExamenes.getBtnOptometria());
        this.frmExamenes.getBtnGrupo().add(frmExamenes.getBtnOdontologia());
        this.frmExamenes.getBtnGrupo().add(frmExamenes.getBtnCorprologico());
        this.frmPrincipal.getBtnBuscar().addActionListener(this);
        this.frmPrincipal.getBtnConsultar().addActionListener(this);
        this.frmPrincipal.getBtnConsultarHistoria().addActionListener(this);
        this.frmPrincipal.getBtnRegistrar().addActionListener(this);
        //this.frmConsultar1.getBtnActualizar().addActionListener(this);
        this.frmConsultar1.getBtnEliminar().addActionListener(this);
        examenes = new ArrayList<Examen>();
        auxH = new Hospitalizacion();
        auxL = new Laboratorios();
        this.total = 0;
        this.conexionbd = new HospitalDAO();
        this.hilo1 = new Thread(this);
        this.hora = new Hora();
    }
    
    /**
     *  Método que inicia y establece el titulo de la ventana Principal
     */
    public void iniciar(){
        frmPrincipal.setTitle("Hospital");
        frmPrincipal.setVisible(true);
        hilo1.start();
        
    }

    /**
     * Método para el control de acciones del formulario
     * @param ae actionEventa para detercatr eventos en las vistas
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == frmPrincipal.getOpcmRegistrar()  || ae.getSource() == frmPrincipal.getBtnRegistrar()){
            frmConsultar.getTblConsulta().setModel(conexionbd.consultar());
            frmRegistrar.getTxtNro().setText(String.valueOf(frmConsultar.getTblConsulta().getRowCount() + 1));
            abrirVentana(frmRegistrar);
        }
        if(ae.getSource() == frmPrincipal.getOpcmConsultar()){//{
            //try {
                frmConsultar.getTblConsulta().setModel(conexionbd.consultar());
                //agregarDatos(frmConsultar.getTblConsulta());
                //agregarDatosPersistencia(frmConsultar.getTblConsulta());
            //} catch (IOException ex) {
              //  JOptionPane.showMessageDialog(frmPrincipal, "Error al abrir el archivo");
            //}
            frmConsultar.getTxtTotal().setText(String.valueOf(this.total));
            abrirVentana(frmConsultar);
        }
        if(ae.getSource() == frmPrincipal.getOpcmConsultarHistoria())
        {
            abrirVentana(frmConsultar1);
            frmConsultar1.getTblHistorias().setModel(conexionbd.consultarHistorias());
            frmConsultar1.getTblPacientes().setModel(conexionbd.consultarPacientes());
            frmConsultar1.getTblServicios().setModel(conexionbd.consultarServicios());
            frmConsultar1.getTblHospitalizaciones().setModel(conexionbd.consultarHospitalizaciones());
            frmConsultar1.getTblLabs().setModel(conexionbd.consultarExamenes());
            frmConsultar1.getTxtTotal().setText(String.valueOf(conexionbd.getTotal()));
        }
        if(ae.getSource() == frmPrincipal.getOpcmSalir()){
            System.exit(0);
        }
        if(ae.getSource() == frmRegistrar.getBtnFechaSistema())
        {
            Fecha fecha = new Fecha();
            frmRegistrar.getTxtDia().setText(String.valueOf(fecha.getDd()));
            frmRegistrar.getTxtMes().setText(String.valueOf(fecha.getMm()));
            frmRegistrar.getTxtAno().setText(String.valueOf(fecha.getAa()));
        }
        if(ae.getSource() == frmRegistrar.getBtnRegistrar()){
            ArchPdf pdf = new ArchPdf();
            HistoriaClinica historia = new HistoriaClinica();
            Paciente objP = null;
            Servicio objS = null;  
            boolean flag = false;
            try
            {//excepcion para control de fecha
                Fecha fecha = new Fecha(Integer.parseInt(frmRegistrar.getTxtDia().getText()),Integer.parseInt(frmRegistrar.getTxtMes().getText()),Integer.parseInt(frmRegistrar.getTxtAno().getText()));
                //se divio en vez de usar el parametrico para poder controlar las excepciones
                
                historia.setNroHistoria(Integer.parseInt(frmRegistrar.getTxtNro().getText()));
                historia.setFecha(fecha);
                //objR.getListaH().add(new HistoriaClinica(frmRegistrar.getTxtNro().getText(),
                                                 //fecha,
            //                                     objP,objS));
            }
            catch(NumberFormatException ex)
            {
                String mensaje[] = ex.getMessage().split(":");
                JOptionPane.showMessageDialog(frmPrincipal, "Error, se han introducido valores NO númericos " + mensaje[1]);
                flag = true;
                
            } catch (FormatoEntradaExcepcion ex) {
                JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Fecha)"); 
                flag = true;
            }
            //para poder aplicar la excepcion toca hacerle con set? siempre?
            
            
           //tipo de afiliacón
        switch(frmRegistrar.getCmbAfiliacion().getSelectedIndex()){
            case 0:{
                Sisben sis = new Sisben();
                try {
                    sis.setDireccion(frmRegistrar.getTxtDireccion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Dirección)"); 
                    flag = true;
                }
                try {
                    sis.setIdentificacion(frmRegistrar.getTxtIdentificacion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Identificación)"); 
                    flag = true;
                }
                try {
                    sis.setNombre(frmRegistrar.getTxtNombre().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nombre)"); 
                    flag = true;
                }
                try {
                    sis.setTelefono(frmRegistrar.getTxtTelefono().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Telefono)"); 
                    flag = true;
                }
                objP = sis;
               break;
            }
            case 1:{
                TipoA ta = new TipoA();
                try {
                    ta.setDireccion(frmRegistrar.getTxtDireccion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Dirección)"); 
                    flag = true;
                }
                try {
                    ta.setIdentificacion(frmRegistrar.getTxtIdentificacion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Identificación)"); 
                    flag = true;
                }
                try {
                    ta.setNombre(frmRegistrar.getTxtNombre().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nombre)"); 
                    flag = true;
                }
                try {
                    ta.setTelefono(frmRegistrar.getTxtTelefono().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Telefono)"); 
                    flag = true;
                }
                objP = ta;
                break;
            }
            case 2:{
                TipoB tb = new TipoB();
                try {
                    tb.setDireccion(frmRegistrar.getTxtDireccion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Dirección)"); 
                    flag = true;
                }
                try {
                    tb.setIdentificacion(frmRegistrar.getTxtIdentificacion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Identificación)"); 
                    flag = true;
                }
                try {
                    tb.setNombre(frmRegistrar.getTxtNombre().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nombre)"); 
                    flag = true;
                }
                try {
                    tb.setTelefono(frmRegistrar.getTxtTelefono().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Telefono)"); 
                    flag = true;
                }
                objP = tb;
                break;
            }
            case 3:{
                TipoC tc = new TipoC();
                try {
                    tc.setDireccion(frmRegistrar.getTxtDireccion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Dirección)"); 
                    flag = true;
                }
                try {
                    tc.setIdentificacion(frmRegistrar.getTxtIdentificacion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Identificación)"); 
                    flag = true;
                }
                try {
                    tc.setNombre(frmRegistrar.getTxtNombre().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nombre)"); 
                    flag = true;
                }
                try {
                    tc.setTelefono(frmRegistrar.getTxtTelefono().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Telefono)"); 
                    flag = true;
                }
                objP = tc;
                break;
            }
        }
        
        //objR.getListaH().get(objR.getListaH().size()-1).setDtsPaciente(objP);
        switch(frmRegistrar.getCmbTipoServicio().getSelectedIndex()){
            case 0:{
               //objS = new CitaMedGenr(frmRegistrar.getTxtCodigo().getText(), "Cita Medicina General", frmRegistrar.getTxtaDescripcion().getText()); 
                CitaMedGenr med = new CitaMedGenr();
                try {
                    med.setCodigo(Integer.parseInt(frmRegistrar.getTxtCodigo().getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Cod Servicio)"); 
                    flag = true;
                }
                med.setNombre("Cita Medicina General");
                try {
                    med.setDescripcion(frmRegistrar.getTxtaDescripcion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nom Servicio)"); 
                    flag = true;
                }
                objS = med;
                break;
            }
            case 1:{
               //objS = new Vacunacion(frmRegistrar.getTxtCodigo().getText(), "Vacunacion", frmRegistrar.getTxtaDescripcion().getText()); 
                Vacunacion vac = new Vacunacion();
                try {
                    vac.setCodigo(Integer.parseInt(frmRegistrar.getTxtCodigo().getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Cod Servicio)"); 
                    flag = true;
                }
                vac.setNombre("Vacunacion");
                try {
                    vac.setDescripcion(frmRegistrar.getTxtaDescripcion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nom Servicio)"); 
                    flag = true;
                }
               objS = vac;
               break;
            }
            case 2:{
                    abrirVentana(frmExamenes);//apertura
                Laboratorios lab = new Laboratorios(null, (frmConsultar.getTblConsulta().getRowCount() + 1) , null, null);
                try {
                    lab.setCodigo(Integer.parseInt(frmRegistrar.getTxtCodigo().getText()));
                    auxL.setCodigo(Integer.parseInt(frmRegistrar.getTxtCodigo().getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Cod Servicio)"); 
                    flag = true;
                }
                lab.setNombre("Laboratorio");
                auxL.setNombre("Laboratorio");
                try {
                    lab.setDescripcion(frmRegistrar.getTxtaDescripcion().getText());
                    auxL.setDescripcion(frmRegistrar.getTxtaDescripcion().getText());
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nom Servicio)"); 
                    flag = true;
                }
                objS = lab;
               //objS = new Laboratorios(examenes,frmRegistrar.getTxtCodigo().getText(), "Laboratorio", frmRegistrar.getTxtaDescripcion().getText());
            break;
            }
            case 3:{
                try{
                Hospitalizacion hos = new Hospitalizacion();
                Fecha entrada = new Fecha(Integer.parseInt(frmRegistrar.getTxtDia().getText()),Integer.parseInt(frmRegistrar.getTxtMes().getText()),Integer.parseInt(frmRegistrar.getTxtAno().getText()));
                hos.setIngreso(entrada);
                Fecha salida = new Fecha(Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Dia de salida:","Ingrese fecha de salida [formato dd]",1)),
                                         Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Mes de salida:","Ingrese fecha de salida [formato mm]",1)),
                                         Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Año de salida:","Ingrese fecha de salida [formato aaaa]",1)));
                hos.setSalida(salida);
                hos.setCodigo(Integer.parseInt(frmRegistrar.getTxtCodigo().getText()));
                hos.setNombre("Hospitalizacion");
                hos.setDescripcion(frmRegistrar.getTxtaDescripcion().getText());
                
                /*objS = new Hospitalizacion(new Fecha(Integer.parseInt(frmRegistrar.getTxtDia().getText()),Integer.parseInt(frmRegistrar.getTxtMes().getText()),Integer.parseInt(frmRegistrar.getTxtAno().getText())),
                                           new Fecha(Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Dia de salida:","Ingrese fecha de salida",1)),
                                                     Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Mes de salida:","Ingrese fecha de salida",1)),
                                                     Integer.parseInt(JOptionPane.showInputDialog(frmRegistrar,"Año de salida:","Ingrese fecha de salida",1))), 
                                           frmRegistrar.getTxtCodigo().getText(), "Hospitalizacion", frmRegistrar.getTxtaDescripcion().getText());
                */
                objS = hos;
                }
                catch(NumberFormatException ex)
                {
                    String mensaje[] = ex.getMessage().split(":");
                    JOptionPane.showMessageDialog(frmPrincipal, "Error, se han introducido valores NO númericos " + mensaje[1]);
                    flag = true;
                } catch (FormatoEntradaExcepcion ex) {
                    JOptionPane.showMessageDialog(frmPrincipal, ex.toString() + " (Nom Fecha Salida)"); 
                    flag = true;
                }
            break;    
            }
        }
        if(flag == false)
        {
            historia.setDtsPaciente(objP);
            historia.setDtsServicio(objS);
            objR.getListaH().add(historia);//adición a la lista
            //pdf.crear_PDF(historia);
            
            //objR.getListaH().add(historia);//adición a la lista
            //JOptionPane.showMessageDialog(frmPrincipal, "Historia Clinica Registrada");
            if( !(objR.getListaH().get(objR.getListaH().size()-1).getDtsServicio() instanceof Laboratorios)){
                try{
                    String msj = datos(objR.getListaH().size()-1);
                    conexionbd.setObjH(historia);
                    String insertar = conexionbd.insertar();
                    
                    String insertar2 = conexionbd.insertar2();
                    if("Error al ingreso de datos, llave primaria (DNI) repetida".equals(insertar2))
                    {
                        JOptionPane.showMessageDialog(frmPrincipal, "Historia Clinica NO Registrada\nError al ingreso de datos, llave primaria (DNI) o COD_Servicio repetida");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frmPrincipal, "Historia Clinica Registrada");
                        frmRegistrar.getTxtNro().setText(String.valueOf(frmConsultar.getTblConsulta().getRowCount() + 1));
                        frmRegistrar.getTxtAno().setText("");
                        frmRegistrar.getTxtDia().setText("");
                        frmRegistrar.getTxtMes().setText("");
                        frmRegistrar.getTxtIdentificacion().setText("");
                        frmRegistrar.getTxtTelefono().setText("");
                        frmRegistrar.getTxtaDescripcion().setText("");
                        frmRegistrar.getTxtCodigo().setText(String.valueOf(frmConsultar.getTblConsulta().getRowCount() + 1));
                        frmRegistrar.getTxtDireccion().setText("");
                        frmRegistrar.getTxtNombre().setText("");
                    }
                    System.out.println(insertar);
                    System.out.println(insertar2);
                    con.EscribeDatos(msj, "RegistroHospital.txt");
                    pdf.crear_PDF(historia, objP.getNombre());
                    JOptionPane.showMessageDialog(frmPrincipal, "Se ha generado un recibo en pdf");
                }catch(IOException ex){
                    JOptionPane.showMessageDialog(frmConsultar, "Error al abrir el archivo");
                }   

            }
            //limpieza porque no hay jPanle
            
        }
        
        //objR.getListaH().get(objR.getListaH().size()-1).setDtsServicio(objS);
        
        if(flag == true)
        {
                JOptionPane.showMessageDialog(frmPrincipal, "Paciente no registrado");
                flag = false;
        }
        }
        
     if(ae.getSource() == frmExamenes.getBtnAgregar()){
         Examen ex = null;
                   if(frmExamenes.getBtnSangre().isSelected()){
                       ex=new Examen("101","Sangre",frmExamenes.getTxtDescripcion().getText(),12000);
                   }
                   if(frmExamenes.getBtnOrina().isSelected()){
                      ex=new Examen("102","Orina",frmExamenes.getTxtDescripcion().getText(),10000);
                   }
                   if(frmExamenes.getBtnCorprologico().isSelected()){
                      ex=new Examen("103","Cropológico",frmExamenes.getTxtDescripcion().getText(),15000);
                   }
                   if(frmExamenes.getBtnOptometria().isSelected()){
                     ex=new Examen("104","Optometría",frmExamenes.getTxtDescripcion().getText(),35000);
                   }
                   if(frmExamenes.getBtnOdontologia().isSelected()){
                      ex=new Examen("105","Odontología",frmExamenes.getTxtDescripcion().getText(),50000);
                   }
                //examenes.add(ex);
                
                //auxL.setExamenes(examenes);
                auxL.getExamenes().add(ex);
                objR.getListaH().get(objR.getListaH().size()-1).setDtsServicio(auxL);
                JOptionPane.showMessageDialog(frmExamenes, objR.getListaH().get(objR.getListaH().size()-1).getDtsServicio().toString());
                JOptionPane.showMessageDialog(frmExamenes, "Examen Agregado con exito");
                frmExamenes.getTxtDescripcion().setText("");
                /*try{
                    String msj = datos(objR.getListaH().size()-1);
                    con.EscribeDatos(msj, "RegistroHospital.txt");
                }catch(IOException exx){
                    JOptionPane.showMessageDialog(frmConsultar, "Error al abrir el archivo");
                }*/
     }
     if(ae.getSource() ==frmExamenes.getBtnFinalizar()){
         ArchPdf pdf = new ArchPdf();
         try{
            String msj = datos(objR.getListaH().size()-1);//txt
            conexionbd.setObjH(objR.getListaH().get(objR.getListaH().size()-1));
            String insertar = conexionbd.insertar2();
            System.out.println(insertar);
            con.EscribeDatos(msj, "RegistroHospital.txt");
            pdf.crear_PDF((objR.getListaH().get(objR.getListaH().size()-1)),(objR.getListaH().get(objR.getListaH().size()-1)).getDtsPaciente().getNombre());
        }catch(IOException ex){
            JOptionPane.showMessageDialog(frmConsultar, "Error al abrir el archivo");
        }
        frmExamenes.setVisible(false); 
     }
     if(ae.getSource() == frmPrincipal.getOpcmMedico())
     { 
        String med = JOptionPane.showInputDialog(frmPrincipal, "Nombre médico");
        String data = conexionbd.insertarMedico(med);
        JOptionPane.showMessageDialog(frmPrincipal, data);
     }
     if(ae.getSource() == frmConsultar1.getBtnEliminar())
     {
        JTable tabla = frmConsultar1.getTblPacientes();
          DefaultTableModel modeloDeMiTabla = (DefaultTableModel) tabla.getModel();
              enviarDatosDAO();
          
          int resp=JOptionPane.showConfirmDialog(
          null, "¿Desea eliminar el registro seleccionado?", "Advertencia", 0, JOptionPane.QUESTION_MESSAGE); 
          if (resp==JOptionPane.YES_OPTION)
          {
              JOptionPane.showMessageDialog(frmPrincipal, this.conexionbd.eliminar());
              modeloDeMiTabla.removeRow(tabla.getSelectedRow());
          }
          else
          {
              JOptionPane.showMessageDialog(frmPrincipal, "Registro NO eliminado");
          }
    }
     /*if(ae.getSource() == frmConsultar1.getBtnActualizar())
    {
            enviarDatosDAO();
            JOptionPane.showMessageDialog(frmPrincipal, this.conexionbd.actualizar());

    }*/
     
    }
    
    /**
     * Método para agregar datos a la tabla de consulta de datos
     * @param tabla tabla a modificar
     */
    /*public void agregarDatos(JTable tabla) throws IOException
    {
        String fig = "", ser = "";
        double aux1 = 0;
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.setRowCount(0);
        for(int i=0; i<objR.getListaH().size(); i++)
        {
            if(objR.getListaH().get(i).getDtsPaciente() instanceof Sisben)
            {
                fig = "Sisben";
            }
            else if(objR.getListaH().get(i).getDtsPaciente() instanceof TipoA)
            {
                fig = "Tipo A";
            }
            else if(objR.getListaH().get(i).getDtsPaciente() instanceof TipoB)
            {
                fig = "Tipo B";
            }
            else if(objR.getListaH().get(i).getDtsPaciente() instanceof TipoC)
            {
                fig = "Tipo C";
            }
            
            if(objR.getListaH().get(i).getDtsServicio() instanceof CitaMedGenr)
            {
                ser = "Cita Medicina General";
            }
            else if(objR.getListaH().get(i).getDtsServicio() instanceof Hospitalizacion)
            {
                ser = "Hospitalización";
            }
            else if(objR.getListaH().get(i).getDtsServicio() instanceof Laboratorios)
            {
                ser = "Laboratorio";
            }
            else if(objR.getListaH().get(i).getDtsServicio() instanceof Vacunacion)
            {
                ser = "Vacunación";
            }
            
            if(objR.getListaH().get(i).getDtsServicio() instanceof CitaMedGenr || objR.getListaH().get(i).getDtsServicio() instanceof Vacunacion){
                aux1 = objR.getListaH().get(i).valor();
            }else if(objR.getListaH().get(i).getDtsServicio() instanceof Laboratorios){
                aux1 = objR.getListaH().get(i).valorLAB((Laboratorios)objR.getListaH().get(i).getDtsServicio());
            }else if(objR.getListaH().get(i).getDtsServicio() instanceof Hospitalizacion){
                aux1 = objR.getListaH().get(i).valorHOPS((Hospitalizacion) objR.getListaH().get(i).getDtsServicio());
            }
            Object datos[] = {objR.getListaH().get(i).getNroHistoria(),
                              objR.getListaH().get(i).getFecha().toString(),
                              objR.getListaH().get(i).getDtsPaciente().getNombre(),
                              objR.getListaH().get(i).getDtsPaciente().getIdentificacion(),
                              objR.getListaH().get(i).getDtsPaciente().getDireccion(),
                              objR.getListaH().get(i).getDtsPaciente().getTelefono(),
                              fig, ser, 
                              aux1};
            plantilla.addRow(datos);
            
        }
        frmConsultar.getTxtTotal().setText(""+objR.recaudoTotal());
        
    }*/
    
    /*
    *Metodo para visualizar los datos en la tabla y obtener el total
    *@param tabla tabla donde se visualizan los datos
    */
    public void agregarDatosPersistencia(JTable tabla) throws IOException{
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        plantilla.setRowCount(0);
        String datos = con.leerDatos("RegistroHospital.txt");
        archivoTabla(datos,frmConsultar.getTblConsulta());
        frmConsultar.getTxtTotal().setText(""+objR.recaudoTotal());
    }
    
    /**
     * Método para abrir ventanas internas y controlar sus excepciones
     * @param frm ventana a abrir
     */
    public void abrirVentana(JInternalFrame frm)
    {
        if(frm.isVisible())
        {
            frm.toFront();
            frm.getFocusOwner();
            try{
                frm.setSelected(true); 
            } 
            catch(PropertyVetoException ex)
            {
                JOptionPane.showMessageDialog(frm, "Error al abrir Ventana " + ex.toString());
            }         
        }
        else if(!frmPrincipal.getPndEscritorio().isAncestorOf(frm))
        {
            frmPrincipal.getPndEscritorio().add(frm);
            frm.setVisible(true);
        }
        else
        {
            frmPrincipal.getPndEscritorio().setSelectedFrame(frm);
            frm.setVisible(true);
        }     
    }
    
    /*
    *Metodo para guardar todos los datos del archivo en la tabla del formulario
    *@param datos un string que contiene todo lo leido en del archivo
    *@param tabla para tener la referencia de donde almacenar los datos 
    */
    public void archivoTabla(String datos, JTable tabla){
        this.total = 0;
        DefaultTableModel plantilla = (DefaultTableModel) tabla.getModel();
        String ListaHospital []=datos.split("\n");
        for (int i = 0; i < ListaHospital.length; i++) {
            String historia []= ListaHospital[i].split(";");
            Object fila[] = {historia[0], historia[1],
                historia[2], historia[3],
                historia[4], historia[5],
                historia[6], historia[7],
                Double.parseDouble(historia[8])};
            this.total += Double.parseDouble(historia[8]);
            plantilla.addRow(fila);
        }
    }
    
    /*
    *metodo para obtener los datos especificos que guardaremos dentro del archivo
    *@param i para saber dentro de la lista de historiales clinicos cual usar
    *retunr String
    */
    public String datos(int i){
        String msj = "";
        if(objR.getListaH().get(i).getDtsServicio()instanceof CitaMedGenr || objR.getListaH().get(i).getDtsServicio()instanceof Vacunacion){
         msj = objR.getListaH().get(i).getNroHistoria()+";"
              +objR.getListaH().get(i).getFecha().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().afiliacion()+";"
              +objR.getListaH().get(i).getDtsServicio().getNombre()+";"
              +objR.getListaH().get(i).valor();   
        }
        if(objR.getListaH().get(i).getDtsServicio()instanceof Laboratorios){
         msj = objR.getListaH().get(i).getNroHistoria()+";"
              +objR.getListaH().get(i).getFecha().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().afiliacion()+";"
              +objR.getListaH().get(i).getDtsServicio().tiposervicio()+";"
              +objR.getListaH().get(i).valorLAB((Laboratorios) objR.getListaH().get(i).getDtsServicio());   
        }
        if(objR.getListaH().get(i).getDtsServicio()instanceof Hospitalizacion){
         msj = objR.getListaH().get(i).getNroHistoria()+";"
              +objR.getListaH().get(i).getFecha().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().toString()+";"
              +objR.getListaH().get(i).getDtsPaciente().afiliacion()+";"
              +objR.getListaH().get(i).getDtsServicio().getNombre()+";"
              +objR.getListaH().get(i).valorHOPS((Hospitalizacion) objR.getListaH().get(i).getDtsServicio());   
        }
        return msj;
    }

    @Override
    public void run() {
        try {
            while(hilo1 == Thread.currentThread())
            {
                
                frmPrincipal.getLblHora().setText(hora.toString());
                hora.ActualizaSegundo();
                hora.ActualizaMinuto();
                hora.ActualizaHora();
                hilo1.sleep(1000);
            }
            
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    
    public void enviarDatosDAO()
    {
        int fila = frmConsultar1.getTblPacientes().getSelectedRow();
        HistoriaClinica objH = new HistoriaClinica();
        try {
            objH.setNroHistoria(0);
            objH.setFecha(new Fecha(12, 12, 2020));
            objH.setDtsServicio(new Laboratorios(examenes, fila, "null", "null"));
        } catch (FormatoEntradaExcepcion ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            objH.setDtsPaciente(new Sisben());
        try {
            objH.getDtsPaciente().setIdentificacion(frmConsultar1.getTblPacientes().getValueAt(fila, 0).toString());
            objH.getDtsPaciente().setNombre(frmConsultar1.getTblPacientes().getValueAt(fila, 1).toString());
            objH.getDtsPaciente().setDireccion(frmConsultar1.getTblPacientes().getValueAt(fila, 2).toString());
            objH.getDtsPaciente().setTelefono(frmConsultar1.getTblPacientes().getValueAt(fila, 4).toString());
            this.conexionbd.setObjH(objH);
        } catch (FormatoEntradaExcepcion ex) {
            ex.toString();
        }
        
    }
}