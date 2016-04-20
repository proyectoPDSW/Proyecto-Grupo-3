/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import java.util.ArrayList;
import eci.pdsw.entities.*;

/**
 *
 * @author Zawsx
 */
public interface DAOEquipoSencillo {

    public EquipoSencillo load(String nombre) throws PersistenceException;

    public void save(EquipoSencillo toSave) throws PersistenceException;

    public void update(EquipoSencillo toUpdate) throws PersistenceException;

    public ArrayList<EquipoSencillo> loadAll() throws PersistenceException;

    public int loadByNombreDisponibles(String nombre) throws PersistenceException;
}
