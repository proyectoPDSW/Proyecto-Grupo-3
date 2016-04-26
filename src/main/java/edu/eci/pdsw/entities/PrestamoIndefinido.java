/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamoIndefinido extends Prestamo{
    
    public PrestamoIndefinido(int idPrestamo, Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, List equiposComplejosPrestados, List equiposSencillosPrestados , Persona elQuePideElPrestamo, int tipo_prestamo) {
        this.idPrestamo = idPrestamo;
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

    public PrestamoIndefinido(Persona elQuePideElPrestamo, List equiposComplejosPrestados, Map equiposSencillosPrestados) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        if(equiposComplejosPrestados==null){
            this.equiposComplejosPrestados=new LinkedList<>();
            this.equiposComplejosFaltantes=new LinkedList<>();
        }else{
            this.equiposComplejosPrestados=equiposComplejosPrestados;
            this.equiposComplejosFaltantes=equiposComplejosPrestados;
        }
        if(equiposSencillosPrestados==null){
            this.equiposSencillosPrestados=new HashMap<>();
            this.equiposSencillosFaltantes=new HashMap<>();
        }else{
            this.equiposSencillosPrestados=equiposSencillosPrestados;
            this.equiposSencillosFaltantes=equiposSencillosPrestados;
        }
        this.fechaInicio=new Timestamp(System.currentTimeMillis());
        tipo_prestamo=2;
    }
    

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Aqui esta el id "+idPrestamo + "\n ");
        sb.append(fechaInicio.toString() + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        for (EquipoComplejo equiposComplejosPrestado : equiposComplejosPrestados) {
            sb.append(" "+equiposComplejosPrestado.toString()+" \n");
        }
        if(equiposSencillosPrestados==null) return sb.toString();
        for (Map.Entry<EquipoSencillo,Integer> equiposSencillosPrestado : equiposSencillosPrestados.entrySet()) {
            sb.append(equiposSencillosPrestado.getKey().toString() +" "+equiposSencillosPrestado.getValue()+" \n");
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
