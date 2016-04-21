/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;

/**
 *
 * @author Hugo Alvarez
 */
public interface DAOPrestamos {

    /**
     *
     * @param fecha
     * @param nombre
     * @return
     */
    public Prestamo load(Timestamp fecha, String nombre);
    
}
