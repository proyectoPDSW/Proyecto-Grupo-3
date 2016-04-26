/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamoIndefinido extends Prestamo{

    public PrestamoIndefinido(Persona elQuePideElPrestamo, List equiposComplejosPrestados, Map equiposSencillosPrestados) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        this.equiposComplejosPrestados=equiposComplejosPrestados;
        this.equiposComplejosFaltantes=equiposComplejosPrestados;
        this.equiposSencillosPrestados=equiposSencillosPrestados;
        this.equiposSencillosFaltantes=equiposSencillosPrestados;
        this.fechaInicio=new Timestamp(System.currentTimeMillis());
        tipo_prestamo=2;
    }
    

    @Override
    public String toString() {
        return getElQuePideElPrestamo() + " " + getEquiposComplejosPrestados().toString() + " " + getEquiposSencillosPrestados().toString();
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
