/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Calendar;

/**
 * Clase que permite crear una hora, tomaada de sistema o registrada en formato hh/mm/ss
 * @author Sergio Cruz
 */
public class Hora {
    private int hh,mm,ss;

    /**
     * Constructor paramétrico para registrar la hora por el ususario a partir de valores enteros
     * @param hh hora
     * @param mm minutos
     * @param ss segundos
     */
    public Hora(int hh, int mm, int ss) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
    }
    
    /**
     * Constructor básico para generar la hora a partir de la actual en el sistema
     */
    public Hora() {
        Calendar hora = Calendar.getInstance();
        this.hh = hora.get(Calendar.HOUR_OF_DAY);
        this.mm = hora.get(Calendar.MINUTE);
        this.ss = hora.get(Calendar.SECOND);
    }

    /**
     * Método para obtener la hora en formato hora/minutos/segundos desde valores enteros impresos como string
     * @return String
     */
    @Override
    public String toString() {
        return hh + ":" + mm + ":" + ss;
    }

    /**
     * Método para obtener la hora en valor entero
     * @return int
     */
    public int getHh() {
        return hh;
    }

    /**
     * Método para modificar la hora a partir de un valor entero
     * @param hh hora
     */
    public void setHh(int hh) {
        this.hh = hh;
    }

    /**
     * Método para obtener los minutos en valor entero
     * @return int
     */
    public int getMm() {
        return mm;
    }

    /**
     * Método para modificar los minutos a partir de un valor entero
     * @param mm minutos
     */
    public void setMm(int mm) {
        this.mm = mm;
    }

    /**
     * Método para obtener los segundos en valor entero
     * @return int
     */
    public int getSs() {
        return ss;
    }

    /**
     * Método para modificar los segundos a partir de un valor entero
     * @param ss segundos
     */
    public void setSs(int ss) {
        this.ss = ss;
    }
    
    public void ActualizaSegundo()
    {
        String s = String.valueOf(ss);
        if(ss == 59)
        {
            ss=0;
        }
        else
        {
            ss++;
        }
    }
    
    public void ActualizaMinuto()
    {
        String m = String.valueOf(mm);
        if(mm == 60)
        {
            mm=0;
        }
        else if (ss==0)
        {
            mm++;
        }
    }
    
    public void ActualizaHora()
    {
        String h = String.valueOf(hh);
        if(hh == 24)
        {
            hh=0;
        }
        else if(mm == 59 && ss == 59)
        {
            hh++;
        }
    }
}
