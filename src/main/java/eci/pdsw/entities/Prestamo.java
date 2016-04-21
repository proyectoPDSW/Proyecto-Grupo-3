/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo Alvarez
 */
public abstract class Prestamo {
    protected int idPrestamo;
    protected Date fechaInicio;
    protected Date fechaEstimadaDeEntrega;
    protected Date fechaRealEntregada;
    protected List<EquipoComplejo> equiposComplejosPrestados;
    protected List<EquipoComplejo> equiposComplejosEntregados;
    protected Map<EquipoSencillo,Integer> equiposSencillosPrestados;
    protected Map<EquipoSencillo,Integer> equiposSencillosEntregados;
    protected Persona elQuePideElPrestamo;
    
    @Override
    public abstract String toString();
    /**
     * Obj: Saber si el prestamo esta en mora o no.
     * Pre: Ninguna.
     * Pos: devolver True si el prestamo esta en mora o no.
     * @return True, si el prestamo esta en mora, False d.l.c
     */
    public abstract boolean enMora();
}
