/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * @author Hugo Alvarez
 */
public abstract class Prestamo implements Comparable<Prestamo> {
    protected Timestamp fechaInicio;
    protected Timestamp fechaEstimadaDeEntrega;
    protected Timestamp fechaRealEntregada;

    
    
    protected Set<EquipoComplejo> equiposComplejosPrestados;
    protected Set<EquipoComplejo> equiposComplejosFaltantes;
    protected Set<EquipoSencillo> equiposSencillosPrestados;
    protected Set<EquipoSencillo> equiposSencillosFaltantes;
    protected Persona elQuePideElPrestamo;
    protected String tipo_prestamo;

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
    public static Timestamp currDate(){
        Timestamp timestamp=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SS");                                                                                
   
        fmt.setTimeZone(TimeZone.getTimeZone("GMT-5"));                                                                                              

        System.out.println(fmt.format(timestamp));
        return timestamp;
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
    public Set<EquipoComplejo> getEquiposComplejosFaltantes() {
        if(equiposComplejosFaltantes==null) equiposComplejosFaltantes=new HashSet<>();
        setEquiposComplejosFaltantes(equiposComplejosPrestados);
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
    public Set<EquipoComplejo> getEquiposComplejosPrestados() {
        if(equiposComplejosPrestados!=null){
            setEquiposComplejosFaltantes(equiposComplejosPrestados);
        }
        return equiposComplejosPrestados;
    }

    /**
     * @param equiposComplejosPrestados the equiposComplejosFaltantes to set
     */
    public void setEquiposComplejosFaltantes(Set<EquipoComplejo> equiposComplejosPrestados) {
        //OBTENER LOS EQUIPOS DE DB
        equiposComplejosFaltantes=new HashSet<>();
        if(equiposComplejosPrestados!=null){
            for(EquipoComplejo ec : equiposComplejosPrestados){
                if(ec.getEstado().equalsIgnoreCase(EquipoComplejo.diario) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.indefinido) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.p24h) || ec.getEstado().equalsIgnoreCase(EquipoComplejo.semestre)){
                    equiposComplejosFaltantes.add(ec);
                }
            }
        }       
        //if(terminado())fechaRealEntregada=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
    }


    /**
     * @return the elQuePideElPrestamo
     */
    public Persona getElQuePideElPrestamo() {
        return elQuePideElPrestamo;
    }


    public void setEquiposComplejosPrestados(Set<EquipoComplejo> equiposComplejosP) {
        Set<EquipoComplejo> tmp =new HashSet<>();tmp.addAll(equiposComplejosP);
        equiposComplejosP.addAll(tmp);
        this.equiposComplejosPrestados = equiposComplejosP;
        setEquiposComplejosFaltantes(equiposComplejosPrestados);
    }


    public void setElQuePideElPrestamo(Persona elQuePideElPrestamo) {
        this.elQuePideElPrestamo = elQuePideElPrestamo;
    }
    public void setEquiposSencillosPrestados(Set<EquipoSencillo> equiposSencillosPrestados) {
        this.equiposSencillosPrestados = equiposSencillosPrestados;
        setEquiposSencillosFaltantes(this.equiposSencillosPrestados);
        //Collections.sort(equiposSencillosFaltantes2);
    }

    public String getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(String tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }
    
    @Override
    public abstract int compareTo(Prestamo o);

    /**
     * @return the equiposSencillosFaltantes
     */
    public Set<EquipoSencillo> getEquiposSencillosFaltantes() {
        setEquiposSencillosFaltantes(equiposSencillosFaltantes);
        if(equiposSencillosFaltantes==null) equiposSencillosFaltantes=new HashSet<>();
        //Collections.sort(equiposSencillosFaltantes2);
        return equiposSencillosFaltantes;
    }

    /**
     * @param equiposSencillosFaltantes the equiposSencillosFaltantes2 to set
     */
    public void setEquiposSencillosFaltantes(Set<EquipoSencillo> equiposSencillosFaltantes) {
        this.equiposSencillosFaltantes=new HashSet<>();
        if(equiposSencillosFaltantes!=null){
            System.out.println("Entro con "+equiposSencillosFaltantes.size());
            for(EquipoSencillo es : equiposSencillosFaltantes){
                if(es.getCantidadTotal()>0)
                    this.equiposSencillosFaltantes.add(es);
            }
        }
        System.out.println("AQUI puede ser el error ->>>>>> "+terminado());
        //if(terminado())fechaRealEntregada=new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("GMT-5")).getTimeInMillis());
    }
    /**
     * 
     * @return return the equiposSencillosPrestados2
     */
    public Set<EquipoSencillo> getEquiposSencillosPrestados() {
        if(equiposSencillosPrestados!=null){
            setEquiposSencillosFaltantes(equiposSencillosPrestados);
        }
        return equiposSencillosPrestados;
    }
    
    public boolean terminado(){
        if(equiposSencillosFaltantes==null)equiposSencillosFaltantes=new HashSet<>();
        if(equiposComplejosFaltantes==null)equiposComplejosFaltantes=new HashSet<>();
        return equiposComplejosFaltantes.isEmpty() && equiposSencillosFaltantes.isEmpty();
    }
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaEstimadaDeEntrega(Timestamp fechaEstimadaDeEntrega) {
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
    }

    public void setFechaRealEntregada(Timestamp fechaRealEntregada) {
        this.fechaRealEntregada = fechaRealEntregada;
    }

}
