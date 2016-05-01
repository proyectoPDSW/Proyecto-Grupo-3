/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Hugo Alvarez
 */
public abstract class Prestamo implements Comparable<Prestamo> {
    protected Timestamp fechaInicio;
    protected Timestamp fechaEstimadaDeEntrega;
    protected Timestamp fechaRealEntregada;

    
    protected List<EquipoComplejo> equiposComplejosPrestados;
    protected List<EquipoComplejo> equiposComplejosFaltantes;
    protected List<EquipoSencillo> equiposSencillosPrestados2;
    protected List<EquipoSencillo> equiposSencillosFaltantes2;
    protected List<Integer> equiposSencillosPrestadosCantidad2;
    protected Persona elQuePideElPrestamo;
    protected int tipo_prestamo;

    @Override
    public boolean equals(Object obj) {
        Prestamo p = (Prestamo)obj;
        boolean check = true;
        check=fechaInicio.equals(p.getFechaInicio());
        check = check && elQuePideElPrestamo.equals(p.getElQuePideElPrestamo());
        /*if(equiposComplejosPrestados!=null && p.getEquiposComplejosPrestados()!=null){
            check = check && equiposComplejosPrestados.size()==p.getEquiposComplejosPrestados().size();
            for (int i = 0; i < equiposComplejosPrestados.size(); i++) {
                boolean anothercheck=false;
                for(int j = 0; j < p.getEquiposComplejosPrestados().size(); j++){
                    if(equiposComplejosPrestados.get(i).equals(p.getEquiposComplejosPrestados().get(j))) anothercheck=true;
                }
                check = check && anothercheck;
            }
        }
        if(equiposSencillosPrestados2!=null && p.getEquiposSencillosPrestados2()!=null){
            check = check && equiposSencillosPrestados2.size()==p.getEquiposSencillosPrestados2().size();
            for (int i = 0; i < equiposSencillosPrestados2.size(); i++) {
                boolean anothercheck=false;
                for(int j = 0; j < p.getEquiposSencillosPrestados2().size(); j++){
                    if(equiposSencillosPrestados2.get(i).equals(p.getEquiposSencillosPrestados2().get(j))) anothercheck=true;
                }
                check = check && anothercheck;
            }
        }*/
        return check;
        //return fechaInicio.equals(p.getFechaInicio()) && elQuePideElPrestamo.equals(p.getElQuePideElPrestamo());
    }
    
    @Override
    public abstract String toString();
    /**
     * Obj: Saber si el prestamo esta en mora o no.
     * Pre: Ninguna.
     * Pos: devolver True si el prestamo esta en mora o no.
     * @return True, si el prestamo esta en mora, False d.l.c
     */
    public abstract boolean enMora();

    /**
     * @return the equiposComplejosFaltantes
     */
    public List<EquipoComplejo> getEquiposComplejosFaltantes() {
        if(equiposComplejosFaltantes==null) equiposComplejosFaltantes=new LinkedList<>();
        setEquiposComplejosFaltantes(equiposComplejosPrestados);
        Collections.sort(equiposComplejosFaltantes);
        return equiposComplejosFaltantes;
    }


    /**
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @return the fechaEstimadaDeEntrega
     */
    public Timestamp getFechaEstimadaDeEntrega() {
        return fechaEstimadaDeEntrega;
    }

    /**
     * @return the fechaRealEntregada
     */
    public Timestamp getFechaRealEntregada() {
        return fechaRealEntregada;
    }

    /**
     * @return the equiposComplejosPrestados
     */
    public List<EquipoComplejo> getEquiposComplejosPrestados() {
        if(equiposComplejosPrestados!=null){
            Set<EquipoComplejo> tmp =new HashSet<>();tmp.addAll(equiposComplejosPrestados);
            equiposComplejosPrestados=new LinkedList<>();
            equiposComplejosPrestados.addAll(tmp);
            setEquiposComplejosFaltantes(equiposComplejosPrestados);
        }
        return equiposComplejosPrestados;
    }

    /**
     * @param equiposComplejosFaltantes the equiposComplejosFaltantes to set
     */
    public void setEquiposComplejosFaltantes(List<EquipoComplejo> equiposComplejosPrestados) {
        equiposComplejosFaltantes=new LinkedList<>();
        for(EquipoComplejo ec : equiposComplejosPrestados){
            if(ec.getEstado().equalsIgnoreCase(EquipoComplejo.diario) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.indefinido) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.p24h) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.semestre)){
                equiposComplejosFaltantes.add(ec);
            }
        }
    }


    /**
     * @return the elQuePideElPrestamo
     */
    public Persona getElQuePideElPrestamo() {
        return elQuePideElPrestamo;
    }


    public void setEquiposComplejosPrestados(List<EquipoComplejo> equiposComplejosP) {
        Set<EquipoComplejo> tmp =new HashSet<>();tmp.addAll(equiposComplejosP);
        equiposComplejosP.addAll(tmp);
        this.equiposComplejosPrestados = equiposComplejosP;
        setEquiposComplejosFaltantes(equiposComplejosPrestados);
    }


    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }
    public void setEquiposSencillosPrestados2(List<EquipoSencillo> sencillosPrestados) {
        this.equiposSencillosPrestados2 = sencillosPrestados;
        setEquiposSencillosFaltantes2(equiposSencillosPrestados2);
        //Collections.sort(equiposSencillosFaltantes2);
    }

    public int getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(int tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }
    
    @Override
    public abstract int compareTo(Prestamo o);

    /**
     * @return the equiposSencillosFaltantes2
     */
    public List<EquipoSencillo> getEquiposSencillosFaltantes2() {
        setEquiposSencillosFaltantes2(equiposSencillosFaltantes2);
        if(equiposSencillosFaltantes2==null) equiposSencillosFaltantes2=new LinkedList<>();
        //Collections.sort(equiposSencillosFaltantes2);
        return equiposSencillosFaltantes2;
    }

    /**
     * @param equiposSencillosFaltantes2 the equiposSencillosFaltantes2 to set
     */
    public void setEquiposSencillosFaltantes2(List<EquipoSencillo> equiposSencillosFaltantes2) {
        this.equiposSencillosFaltantes2=new LinkedList<>();
        for(EquipoSencillo es : equiposSencillosFaltantes2){
            if(es.getCantidadTotal()>0)
                this.equiposSencillosFaltantes2.add(es);
        }
    }
    public List<EquipoSencillo> getEquiposSencillosPrestados2() {
        return equiposSencillosPrestados2;
    }

    public List<Integer> getEquiposSencillosPrestadosCantidad2() {
        return equiposSencillosPrestadosCantidad2;
    }
}
