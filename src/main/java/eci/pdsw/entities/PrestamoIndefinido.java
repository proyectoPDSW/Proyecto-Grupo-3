/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

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
        this.equiposSencillosPrestados=equiposSencillosPrestados;
    }
    

    @Override
    public String toString() {
        return getElQuePideElPrestamo() + " " + getEquiposComplejosPrestados().toString() + " " + getEquiposSencillosPrestados().toString();
    }

    @Override
    public boolean enMora() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Prestamo o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
