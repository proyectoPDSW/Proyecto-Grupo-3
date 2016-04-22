/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Hugo Alvarez
 */
public interface DAOPrestamo {
    
    /**
     * 
     * @param fecha
     * @param nombre
     * @return
     * @throws PersistenceException 
     */
    public abstract Prestamo load(Timestamp fecha, String nombre) throws PersistenceException;
    
    public abstract void save(Prestamo prestamo) throws PersistenceException;

    public abstract void update(Prestamo prestamo) throws PersistenceException;
    
    public abstract List<Prestamo> loadAll() throws PersistenceException;
    
    public abstract List<Prestamo> loadByFecha(Timestamp fecha) throws PersistenceException;
    
    public abstract List<Prestamo> loadByNombre(String nombre) throws PersistenceException;
    
    public abstract List<Prestamo> loadMorosos() throws PersistenceException;
    
    public abstract List<Prestamo> loadByEquipoComplejo(EquipoComplejo equipocomplejo) throws PersistenceException;
              
    
}
