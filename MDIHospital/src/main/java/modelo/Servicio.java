/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author dadxc
 */
public abstract class  Servicio {

    /**
     *codigo respectivo de cada servicio
     */
    protected int Codigo;

    /**
     *nombre respectivo al servicio
     */
    protected String Nombre,

    /**
     *descripcion respectiva de cada servicio
     */
    Descripcion;

    /**
     *Constructor parametrico
     * @param Codigo codigo del servicio
     * @param Nombre nombre del servicio
     * @param Descripcion descripcion del servicio
     */
    public Servicio(int Codigo, String Nombre, String Descripcion) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
    }
    
    /**
     *Constructor basico
     */
    public Servicio() {
        this.Codigo = 0;
        this.Nombre = "";
        this.Descripcion = "";
    }

    /**
     *retorna el codigo del servicio
     * @return String
     */
    public int getCodigo() {
        return Codigo;
    }

    /**
     *establece o modifica el codigo del servicio
     * @param Codigo codigo del servicio
     */
    public void setCodigo(int Codigo)
    {
        try
        {
            this.Codigo = Codigo;
        }catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
        }
           
        
    }

    /**
     *retorna el nombre respectivo del servicio
     * @return String
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     *establece o modifica el nombre del servicio
     * @param Nombre nombre del servicio
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     *retorna la descripcion respectiva del servicio
     * @return String
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     *Establece o modifica la descripcion respectiva del servicio
     * @param Descripcion descripcion del servicio
     * @throws modelo.FormatoEntradaExcepcion excepción nulo
     */
    public void setDescripcion(String Descripcion) throws FormatoEntradaExcepcion {
        if(Descripcion.equals(""))
        {
            throw new FormatoEntradaExcepcion(101);
        }
        else
        {
           this.Descripcion = Descripcion;
        }
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Servicio" + "\nCodigo:" + Codigo + "\nNombre:" + Nombre + "\nDescripcion:" + Descripcion;
    }
    
    /**
     *metodo abstracto para que cada servicio especifique su tipo de servicio hospitalario
     * @return String
     */
    public abstract String tiposervicio();
    
}
