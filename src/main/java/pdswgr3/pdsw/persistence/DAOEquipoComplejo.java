/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdswgr3.pdsw.persistence;

import java.util.ArrayList;
import pdswgr3.pdsw.entities.EquipoComplejo;

/**
 *
 * @author Zawsx
 */
public interface DAOEquipoComplejo {
    public EquipoComplejo load(String serial);
    public EquipoComplejo load(int placa);
    public void save(EquipoComplejo toSave);
    public void update(EquipoComplejo toUpdate);
    public void reemplazar(EquipoComplejo old,EquipoComplejo novo);
    public ArrayList<EquipoComplejo> loadAll();
    public ArrayList<EquipoComplejo> loadDisponibles();
    public ArrayList<EquipoComplejo> loadByModelo(String modelo);
}
