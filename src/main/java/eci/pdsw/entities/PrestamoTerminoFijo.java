/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo
 */
public class PrestamoTerminoFijo extends Prestamo {

    public PrestamoTerminoFijo(Persona elQuePideElPrestamo, List equiposComplejosPrestados, Map equiposSencillosPrestados, Timestamp fechaEstimadaDeEntrega) {
        this.elQuePideElPrestamo=elQuePideElPrestamo;
        this.equiposComplejosPrestados=equiposComplejosPrestados;
        this.equiposComplejosFaltantes=equiposComplejosPrestados;
        this.equiposSencillosPrestados=equiposSencillosPrestados;
        this.fechaEstimadaDeEntrega=fechaEstimadaDeEntrega;
        this.fechaInicio=new Timestamp(System.currentTimeMillis());
    }
    
    

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return getElQuePideElPrestamo() + " " + getEquiposComplejosPrestados().toString() + " " + getEquiposSencillosPrestados().toString()+ " " + getFechaEstimadaDeEntrega();
    }

    @Override
    public boolean enMora() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
