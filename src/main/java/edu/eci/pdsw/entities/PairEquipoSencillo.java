/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

/**
 *
 * @author Hugo
 */
public class PairEquipoSencillo implements Comparable<PairEquipoSencillo>{
    EquipoSencillo a;
    int b;

    public PairEquipoSencillo(EquipoSencillo a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(PairEquipoSencillo o) {
        return a.compareTo(o.a);
    }

    
}
