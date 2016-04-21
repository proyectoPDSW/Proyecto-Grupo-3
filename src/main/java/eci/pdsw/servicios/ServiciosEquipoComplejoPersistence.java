/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.servicios;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Modelo;
import eci.pdsw.persistence.DAOEquipoComplejo;
import eci.pdsw.persistence.DAOFactory;
import eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2107803
 */
public class ServiciosEquipoComplejoPersistence extends ServiciosEquipoComplejo {

    private final DAOFactory dao;
    private DAOEquipoComplejo complejoPersistencia;

    public ServiciosEquipoComplejoPersistence() {
        InputStream input = null;
        Properties properties = new Properties();
        try {
            ServiciosEquipoComplejoPersistence.class.getClassLoader().getResource("applicationconfig.properties").openStream();
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(ServiciosEquipoComplejoPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = DAOFactory.getInstance(properties);
    }

    @Override
    public void registrarModelo(Modelo model) throws ExcepcionServicios {
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            complejoPersistencia.save(model);
            dao.commitTransaction();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public void registrarEquipoComplejo(EquipoComplejo equipo) throws ExcepcionServicios {
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            complejoPersistencia.save(equipo);
            dao.commitTransaction();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public List<EquipoComplejo> consultarTodo() throws ExcepcionServicios {
        ArrayList<EquipoComplejo> ans=new ArrayList<>();
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.loadAll();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
        return ans;
    }

    @Override
    public List<EquipoComplejo> consultarPorModelo(String modelo) throws ExcepcionServicios {
        ArrayList<EquipoComplejo> ans=new ArrayList<>();
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.loadByModelo(modelo);
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
        return ans;
    }

    @Override
    public EquipoComplejo consultarPorPlaca(int numPlaca) throws ExcepcionServicios {
        EquipoComplejo ans;
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.load(numPlaca);
            dao.commitTransaction();
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public EquipoComplejo consultarPorSerial(String serial) throws ExcepcionServicios {
        EquipoComplejo ans;
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.load(serial);
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public List<EquipoComplejo> consultarDisponibles() throws ExcepcionServicios {
        ArrayList<EquipoComplejo> ans=new ArrayList<>();
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.loadDisponibles();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
        return ans;
    }

    @Override
    public void actualizarEquipo(EquipoComplejo toUpdate) throws ExcepcionServicios {
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            complejoPersistencia.update(toUpdate);
            dao.commitTransaction();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public Modelo consultarModelo(String nombre) throws ExcepcionServicios {
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            Modelo ans=complejoPersistencia.loadModelo(nombre);
            dao.commitTransaction();
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

}
