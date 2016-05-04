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
import java.util.HashSet;
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
    
    public PrestamoTerminoFijo(Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, Set equiposComplejosPrestados, Set equiposSencillosPrestados , Persona elQuePideElPrestamo, String tipo_prestamo) {
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
        this.fechaRealEntregada = fechaRealEntregada;
        if(equiposComplejosPrestados==null){
            this.equiposComplejosPrestados=new HashSet<>();
            this.equiposComplejosFaltantes=new HashSet<>();
        }else{
            this.equiposComplejosPrestados=equiposComplejosPrestados;
            this.equiposComplejosFaltantes=equiposComplejosPrestados;
        }
        if(equiposSencillosPrestados==null)
            this.equiposSencillosPrestados2=new HashSet<>();
        else
            this.equiposSencillosPrestados2 = equiposSencillosPrestados;
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }

    public PrestamoTerminoFijo() {
    }

    public PrestamoTerminoFijo(Persona elQuePideElPrestamo, Set equiposComplejosPrestados, Set equiposSencillosPrestados, Timestamp fechaEstimadaDeEntrega) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        if(equiposComplejosPrestados==null){
            this.equiposComplejosPrestados=new HashSet<>();
            this.equiposComplejosFaltantes=new HashSet<>();
        }else{
            this.equiposComplejosPrestados=equiposComplejosPrestados;
            this.equiposComplejosFaltantes=equiposComplejosPrestados;
        }
        if(equiposSencillosPrestados==null){
            this.equiposSencillosPrestados2=new HashSet<>();
            this.equiposSencillosFaltantes2=new HashSet<>();
        }else{
            this.equiposSencillosPrestados2=equiposSencillosPrestados;
            this.equiposSencillosFaltantes2=equiposSencillosPrestados;
        }
        this.fechaEstimadaDeEntrega=fechaEstimadaDeEntrega;
        this.fechaInicio=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
        tipo_prestamo=EquipoComplejo.diario;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fechaInicio + "\n ");
        sb.append(fechaEstimadaDeEntrega + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        if(equiposComplejosPrestados!=null)
            for (EquipoComplejo ec:equiposComplejosPrestados) {
                sb.append(" "+ec.toString()+" \n");
            }
        if(equiposSencillosPrestados2!=null)
            for (EquipoSencillo es:equiposSencillosPrestados2) {
                sb.append(" "+es.toString()+" \n");
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
