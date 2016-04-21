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
 * @author Hugo
 */
public class PrestamoTerminoFijo extends Prestamo {

    public PrestamoTerminoFijo(Persona elQuePideElPrestamo, List equiposComplejosPrestados, Map equiposSencillosPrestados, Date fechaEstimadaDeEntrega) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        this.equiposComplejosPrestados=equiposComplejosPrestados;
        this.equiposSencillosPrestados=equiposSencillosPrestados;
        this.fechaEstimadaDeEntrega=fechaEstimadaDeEntrega;
    }
    
    

    @Override
    public String toString() {
        return getElQuePideElPrestamo() + " " + getEquiposComplejosPrestados().toString() + " " + getEquiposSencillosPrestados().toString()+ " " + getFechaEstimadaDeEntrega();
    }

    @Override
    public boolean enMora() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
