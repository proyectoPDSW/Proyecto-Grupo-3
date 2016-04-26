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
    protected int idPrestamo;
    protected Timestamp fechaInicio;
    protected Timestamp fechaEstimadaDeEntrega;
    protected Timestamp fechaRealEntregada;

    
    protected List<EquipoComplejo> equiposComplejosPrestados;
    protected List<EquipoComplejo> equiposComplejosFaltantes;
    protected Map<EquipoSencillo,Integer> equiposSencillosPrestados;
    protected Map<EquipoSencillo,Integer> equiposSencillosFaltantes;
    protected List<EquipoSencillo> equiposSencillosPrestados2;
    protected List<EquipoSencillo> equiposSencillosFaltantes2;
    protected List<Integer> equiposSencillosPrestadosCantidad2;
    protected Persona elQuePideElPrestamo;
    protected int tipo_prestamo;

    @Override
    public boolean equals(Object obj) {
        Prestamo p = (Prestamo)obj;
        return fechaInicio.equals(p.getFechaInicio()) && elQuePideElPrestamo.equals(p.getElQuePideElPrestamo());
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
        System.out.println("Entro a los equipos complejos Faltantes");
        if(equiposComplejosFaltantes==null) equiposComplejosFaltantes=new LinkedList<>();
        equiposComplejosFaltantes=getEquiposComplejosPrestados();
        Collections.sort(equiposComplejosFaltantes);
        return equiposComplejosFaltantes;
    }

    /**
     * @return the equiposSencillosFaltantes
     */
    public Map<EquipoSencillo,Integer> getEquiposSencillosFaltantes() {
        System.out.println("Entro a los equipos sencillos Faltantes mapa");
        return equiposSencillosFaltantes;
    }

    /**
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        System.out.println("Entro a fecha inicio");
        return fechaInicio;
    }

    /**
     * @return the fechaEstimadaDeEntrega
     */
    public Timestamp getFechaEstimadaDeEntrega() {
        System.out.println("Entro a fecha estimada de entraga");
        return fechaEstimadaDeEntrega;
    }

    /**
     * @return the fechaRealEntregada
     */
    public Timestamp getFechaRealEntregada() {
        System.out.println("Entro a fecha real entregada");
        return fechaRealEntregada;
    }

    /**
     * @return the equiposComplejosPrestados
     */
    public List<EquipoComplejo> getEquiposComplejosPrestados() {
        System.out.println("Entro a los equipos complejos Prestados");
        if(equiposComplejosPrestados!=null){
            Set<EquipoComplejo> tmp =new HashSet<>();tmp.addAll(equiposComplejosPrestados);
            System.out.println("set: "+Arrays.toString(tmp.toArray()));
            System.out.println("arr: "+Arrays.toString(equiposComplejosPrestados.toArray()));
            equiposComplejosPrestados=new LinkedList<>();
            equiposComplejosPrestados.addAll(tmp);
            equiposComplejosFaltantes=equiposComplejosPrestados;
            System.out.println("arr after: "+Arrays.toString(equiposComplejosPrestados.toArray()));
        }
        return equiposComplejosPrestados;
    }

    /**
     * @param equiposComplejosFaltantes the equiposComplejosFaltantes to set
     */
    public void setEquiposComplejosFaltantes(List<EquipoComplejo> equiposComplejosFaltantes) {
        System.out.println("Entro a  set los equipos complejos Faltantes");
        this.equiposComplejosFaltantes = equiposComplejosFaltantes;
    }

    /**
     * @return the equiposSencillosPrestados
     */
    public Map<EquipoSencillo,Integer> getEquiposSencillosPrestados() {
        System.out.println("Entro a los equipos sencillos prestamos mapa");
        return equiposSencillosPrestados;
    }

    /**
     * @return the elQuePideElPrestamo
     */
    public Persona getElQuePideElPrestamo() {
        System.out.println("Entro a la persona");
        return elQuePideElPrestamo;
    }

    public int getIdPrestamo() {
        System.out.println("Entro a el id del prestamo");
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        System.out.println("Entro a set id del prestamo");
        this.idPrestamo = idPrestamo;
    }

    public void setEquiposComplejosPrestados(List<EquipoComplejo> equiposComplejosP) {
        Set<EquipoComplejo> tmp =new HashSet<>();tmp.addAll(equiposComplejosP);
        equiposComplejosP.addAll(tmp);
        this.equiposComplejosPrestados = equiposComplejosP;
        equiposComplejosFaltantes=equiposComplejosP;
    }

    public void setEquiposSencillosPrestados(Map<EquipoSencillo, Integer> equiposSencillosPrestados) {
        this.equiposSencillosPrestados = equiposSencillosPrestados;
    }

    public void setEquiposSencillosFaltantes(Map<EquipoSencillo, Integer> equiposSencillosFaltantes) {
        this.equiposSencillosFaltantes = equiposSencillosFaltantes;
    }

    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        System.out.println("Entro a set persona");
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }
    public void setEquiposSencillosPrestados2(List<EquipoSencillo> sencillosFaltantes) {
        System.out.println("Entro a equipos sencillos faltantes 2");
        this.equiposSencillosPrestados2 = sencillosFaltantes;
        this.equiposSencillosFaltantes2=sencillosFaltantes;
        Collections.sort(equiposSencillosFaltantes2);
        System.out.println(Arrays.toString(equiposSencillosFaltantes2.toArray()));
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
        System.out.println("Entro a equipos sencillos faltantes 2");
        if(equiposSencillosFaltantes2==null) equiposSencillosFaltantes2=new LinkedList<>();
        Collections.sort(equiposSencillosFaltantes2);
        return equiposSencillosFaltantes2;
    }

    /**
     * @param equiposSencillosFaltantes2 the equiposSencillosFaltantes2 to set
     */
    public void setEquiposSencillosFaltantes2(List<EquipoSencillo> equiposSencillosFaltantes2) {
        System.out.println("Entro a set equipos sencillos faltantes 2");
        this.equiposSencillosFaltantes2 = equiposSencillosFaltantes2;
    }
    public List<EquipoSencillo> getEquiposSencillosPrestados2() {
        System.out.println("Entro a equipos sencillos Prestados 2");
        /*if(equiposSencillosPrestados2==null) equiposSencillosPrestados2=new LinkedList<>();
        Collections.sort(equiposSencillosPrestados2);*/
        return equiposSencillosPrestados2;
    }

    public List<Integer> getEquiposSencillosPrestadosCantidad2() {
        System.out.println("Entro a equipos sencillos Prestados cantidad 2");
        return equiposSencillosPrestadosCantidad2;
    }
}
