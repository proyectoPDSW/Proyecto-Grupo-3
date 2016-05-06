/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamoIndefinido extends Prestamo{
    
    public PrestamoIndefinido(Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, Set equiposComplejosPrestados, Set equiposSencillosPrestados , Persona elQuePideElPrestamo, String tipo_prestamo) {
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
        this.fechaRealEntregada = fechaRealEntregada;
        if(equiposComplejosPrestados==null)
            this.equiposComplejosPrestados=new HashSet<EquipoComplejo>();
        else
            this.equiposComplejosPrestados = equiposComplejosPrestados;
        if(equiposSencillosPrestados==null)
            this.equiposSencillosPrestados2=new HashSet<>();
        else
            this.equiposSencillosPrestados2 = equiposSencillosPrestados;
        this.elQuePideElPrestamo = elQuePideElPrestamo;
        this.tipo_prestamo=tipo_prestamo;
    }

    public PrestamoIndefinido() {
    }

    public PrestamoIndefinido(Persona elQuePideElPrestamo, Set equiposComplejosPrestados, Set equiposSencillosPrestados) {
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
        this.fechaInicio=Prestamo.currDate();
        tipo_prestamo=EquipoComplejo.indefinido;
    }
    

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fechaInicio.toString() + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        if(equiposComplejosPrestados!=null)
            for (EquipoComplejo ec: equiposComplejosPrestados) {
                sb.append(" "+ec.toString()+" \n");
            }
        if(equiposSencillosPrestados2!=null)
            for (EquipoSencillo es: equiposSencillosPrestados2) {
                sb.append(" "+es.toString()+" \n");
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
