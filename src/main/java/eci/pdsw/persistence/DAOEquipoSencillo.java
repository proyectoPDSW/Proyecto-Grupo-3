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
    public EquipoSencillo load(String nombre);
    public void save(EquipoSencillo toSave);
    public void update(EquipoSencillo toUpdate);
    public ArrayList<EquipoSencillo> loadAll();
}
