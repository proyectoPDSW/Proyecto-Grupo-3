/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * @author Hugo
 */
public class PrestamoTerminoFijo extends Prestamo {
    
    public PrestamoTerminoFijo(Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, List equiposComplejosPrestados, List equiposSencillosPrestados , Persona elQuePideElPrestamo, int tipo_prestamo) {
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
        this.fechaRealEntregada = fechaRealEntregada;
        if(equiposComplejosPrestados==null){
            this.equiposComplejosPrestados=new LinkedList<>();
            this.equiposComplejosFaltantes=new LinkedList<>();
        }else{
            this.equiposComplejosPrestados=equiposComplejosPrestados;
            this.equiposComplejosFaltantes=equiposComplejosPrestados;
        }
        if(equiposSencillosPrestados==null)
            this.equiposSencillosPrestados2=new LinkedList<>();
        else
            this.equiposSencillosPrestados2 = equiposSencillosPrestados;
        this.elQuePideElPrestamo = elQuePideElPrestamo;
        this.tipo_prestamo = 1;
    }

    public PrestamoTerminoFijo() {
    }

    public PrestamoTerminoFijo(Persona elQuePideElPrestamo, List equiposComplejosPrestados, List equiposSencillosPrestados, Timestamp fechaEstimadaDeEntrega) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        if(equiposComplejosPrestados==null){
            this.equiposComplejosPrestados=new LinkedList<>();
            this.equiposComplejosFaltantes=new LinkedList<>();
        }else{
            this.equiposComplejosPrestados=equiposComplejosPrestados;
            this.equiposComplejosFaltantes=equiposComplejosPrestados;
        }
        if(equiposSencillosPrestados==null){
            this.equiposSencillosPrestados2=new LinkedList<>();
            this.equiposSencillosFaltantes2=new LinkedList<>();
        }else{
            this.equiposSencillosPrestados2=equiposSencillosPrestados;
            this.equiposSencillosFaltantes2=equiposSencillosPrestados;
        }
        this.fechaEstimadaDeEntrega=fechaEstimadaDeEntrega;
        this.fechaInicio=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
        tipo_prestamo=1;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fechaInicio + "\n ");
        sb.append(fechaEstimadaDeEntrega + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        if(equiposComplejosPrestados!=null)
            for (int i = 0; i < equiposComplejosPrestados.size(); i++) {
                sb.append(" "+equiposComplejosPrestados.get(i).toString()+" \n");
            }
        if(equiposSencillosPrestados2!=null)
            for (int i = 0; i < equiposSencillosPrestados2.size(); i++) {
                sb.append(" "+equiposSencillosPrestados2.get(i).toString()+" \n");
            }
        sb.append(fechaEstimadaDeEntrega+"\n");
        return sb.toString();
    }

    @Override
    public boolean enMora() {
        if(fechaEstimadaDeEntrega.before(new Timestamp(new Date().getTime()))) return true;
        return false;
    }

    @Override
    public int compareTo(Prestamo o) {
        Timestamp curr=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
        int hoursCurr=curr.getHours()+curr.getDay()*24+curr.getMonth()*30*24+curr.getYear()*12*30*24;
        Timestamp f=getFechaEstimadaDeEntrega();
        int hoursThis=f.getHours()+f.getDay()*24+f.getMonth()*30*24+f.getYear()*12*30*24;
        Timestamp of=o.getFechaEstimadaDeEntrega();
        int hoursOther=of.getHours()+of.getDay()*24+of.getMonth()*30*24+of.getYear()*12*30*24;
        if(hoursThis<hoursOther) return 1;
        else return -1;
    }
    
}
