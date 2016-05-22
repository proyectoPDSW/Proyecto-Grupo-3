/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamoIndefinido extends Prestamo {

    /**
     * Constructor de prestamo indefinido
     *
     * @param fechaInicio
     * @param fechaEstimadaDeEntrega
     * @param fechaRealEntregada
     * @param equiposComplejosPrestados
     * @param equiposSencillosPrestados
     * @param elQuePideElPrestamo
     * @param tipo_prestamo
     * @throws PrestamoException
     */
    public PrestamoIndefinido(Timestamp fechaInicio, Timestamp fechaEstimadaDeEntrega, Timestamp fechaRealEntregada, Set equiposComplejosPrestados, Set equiposSencillosPrestados, Persona elQuePideElPrestamo, String tipo_prestamo) throws PrestamoException{
        if(fechaInicio==null) throw new PrestamoException("La fecha inicio no puede ser vacia");
        if(elQuePideElPrestamo==null) throw new PrestamoException("La persona no puede ser nulo");
        if(tipo_prestamo==null || tipo_prestamo.length()==0) throw new PrestamoException("El tipo de prestamo no puede ser nulo");

        
            
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaDeEntrega = fechaEstimadaDeEntrega;
        this.fechaRealEntregada = fechaRealEntregada;
        if (equiposComplejosPrestados == null) {
            this.equiposComplejosPrestados = new HashSet<>();
        } else {
            this.equiposComplejosPrestados = equiposComplejosPrestados;
        }
        if (equiposSencillosPrestados == null) {
            this.equiposSencillosPrestados = new HashSet<>();
        } else {
            this.equiposSencillosPrestados = equiposSencillosPrestados;
        }
        this.elQuePideElPrestamo = elQuePideElPrestamo;
        this.tipo_prestamo = tipo_prestamo;
    }

    public PrestamoIndefinido() {
    }

    /**
     * Constructor de prestamo indefinido
     *
     * @param elQuePideElPrestamo
     * @param equiposComplejosPrestados
     * @param equiposSencillosPrestados
     * @throws PrestamoException
     */
    public PrestamoIndefinido(Persona elQuePideElPrestamo, Set equiposComplejosPrestados, Set equiposSencillosPrestados) throws PrestamoException {
        if(elQuePideElPrestamo==null) throw new PrestamoException("La persona no puede ser nulo");
        this.elQuePideElPrestamo = elQuePideElPrestamo;
        if (equiposComplejosPrestados == null) {
            this.equiposComplejosPrestados = new HashSet<>();
            this.equiposComplejosFaltantes = new HashSet<>();
        } else {
            this.equiposComplejosPrestados = equiposComplejosPrestados;
            this.equiposComplejosFaltantes = equiposComplejosPrestados;
        }
        if (equiposSencillosPrestados == null) {
            this.equiposSencillosPrestados = new HashSet<>();
            this.equiposSencillosFaltantes = new HashSet<>();
        } else {
            this.equiposSencillosPrestados = equiposSencillosPrestados;
            this.equiposSencillosFaltantes = equiposSencillosPrestados;
        }
        this.fechaInicio = Prestamo.currDate();
        tipo_prestamo = EquipoComplejo.indefinido;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(fechaInicio.toString() + "\n ");
        sb.append(getElQuePideElPrestamo().toString() + "\n ");
        if (equiposComplejosPrestados != null) {
            for (EquipoComplejo ec : equiposComplejosPrestados) {
                sb.append(" " + ec.toString() + " \n");
            }
        }
        if (equiposSencillosPrestados != null) {
            for (EquipoSencillo es : equiposSencillosPrestados) {
                sb.append(" " + es.toString() + " \n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean enMora() {
        return false;
    }

    @Override
    public int compareTo(Prestamo o) {
        if (fechaInicio.before(o.getFechaInicio())) {
            return -1;
        } else {
            return 1;
        }
    }

}
