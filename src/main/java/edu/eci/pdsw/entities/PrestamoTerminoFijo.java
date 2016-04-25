/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Hugo
 */
public class PrestamoTerminoFijo extends Prestamo {

    public PrestamoTerminoFijo(Persona elQuePideElPrestamo, List equiposComplejosPrestados, Map equiposSencillosPrestados, Timestamp fechaEstimadaDeEntrega) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        this.equiposComplejosPrestados=equiposComplejosPrestados;
        this.equiposComplejosFaltantes=equiposComplejosPrestados;
        this.equiposSencillosPrestados=equiposSencillosPrestados;
        this.equiposSencillosFaltantes=equiposSencillosPrestados;
        this.fechaEstimadaDeEntrega=fechaEstimadaDeEntrega;
        this.fechaInicio=new Timestamp(System.currentTimeMillis());
    }
    
    

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        for (EquipoComplejo equiposComplejosPrestado : equiposComplejosPrestados) {
            sb.append(" "+equiposComplejosPrestado.toString()+" \n");
        }
        for (Entry<EquipoSencillo,Integer> equiposSencillosPrestado : equiposSencillosPrestados.entrySet()) {
            sb.append(equiposSencillosPrestado.getKey().toString() +" "+equiposSencillosPrestado.getValue()+" \n");
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
        Timestamp curr=new Timestamp(System.currentTimeMillis());
        int hoursCurr=curr.getHours()+curr.getDay()*24+curr.getMonth()*30*24+curr.getYear()*12*30*24;
        Timestamp f=getFechaEstimadaDeEntrega();
        int hoursThis=f.getHours()+f.getDay()*24+f.getMonth()*30*24+f.getYear()*12*30*24;
        Timestamp of=o.getFechaEstimadaDeEntrega();
        int hoursOther=of.getHours()+of.getDay()*24+of.getMonth()*30*24+of.getYear()*12*30*24;
        if(hoursThis<hoursOther) return 1;
        else return -1;
    }
    
}
