/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamoIndefinido extends Prestamo{
    
    public PrestamoIndefinido(Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, List equiposComplejosPrestados, List equiposSencillosPrestados , Persona elQuePideElPrestamo, int tipo_prestamo) {
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
        this.fechaRealEntregada = fechaRealEntregada;
        if(equiposComplejosPrestados==null)
            this.equiposComplejosPrestados=new LinkedList<EquipoComplejo>();
        else
            this.equiposComplejosPrestados = equiposComplejosPrestados;
        if(equiposSencillosPrestados==null)
            this.equiposSencillosPrestados2=new LinkedList<>();
        else
            this.equiposSencillosPrestados2 = equiposSencillosPrestados;
        this.elQuePideElPrestamo = elQuePideElPrestamo;
        this.tipo_prestamo = 2;
    }

    public PrestamoIndefinido() {
    }

    public PrestamoIndefinido(Persona elQuePideElPrestamo, List equiposComplejosPrestados, List equiposSencillosPrestados) {
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
        this.fechaInicio=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
        tipo_prestamo=2;
    }
    

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fechaInicio.toString() + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        if(equiposComplejosPrestados!=null)
            for (int i = 0; i < equiposComplejosPrestados.size(); i++) {
                sb.append(" "+equiposComplejosPrestados.get(i).toString()+" \n");
            }
        if(equiposSencillosPrestados2!=null)
            for (int i = 0; i < equiposSencillosPrestados2.size(); i++) {
                sb.append(" "+equiposSencillosPrestados2.get(i).toString()+" \n");
            }
        return sb.toString();
    }

    @Override
    public boolean enMora() {
        return false;
    }

    @Override
    public int compareTo(Prestamo o) {
        if(fechaInicio.before(o.getFechaInicio())) return -1;
        else return 1;
    }
    
}
