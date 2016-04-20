/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import java.util.ArrayList;
import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Modelo;

/**
 *
 * @author Zawsx
 */
public interface DAOEquipoComplejo {

    public EquipoComplejo load(String serial) throws PersistenceException;

    public EquipoComplejo load(int placa) throws PersistenceException;

    public void save(EquipoComplejo toSave) throws PersistenceException;

    public void update(EquipoComplejo toUpdate) throws PersistenceException;

    public void reemplazar(EquipoComplejo old, EquipoComplejo novo) throws PersistenceException;

    public ArrayList<EquipoComplejo> loadAll() throws PersistenceException;

    public ArrayList<EquipoComplejo> loadDisponibles() throws PersistenceException;

    public ArrayList<EquipoComplejo> loadByModelo(String modelo) throws PersistenceException;
    
    public void save(Modelo model) throws PersistenceException;
}
