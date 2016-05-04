/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.PersistenceException;
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
            input=ServiciosEquipoComplejoPersistence.class.getClassLoader().getResource("applicationconfig.properties").openStream();
            properties.load(input);
        } catch (IOException ex) {
            Registro.anotar(ex);
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
        } catch (PersistenceException  ex) {
            dao.rollbackTransaction();
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            dao.rollbackTransaction();
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
    }

    @Override
    public EquipoComplejo consultarPorSerial(String modelo,String serial) throws ExcepcionServicios {
        EquipoComplejo ans;
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            ans=complejoPersistencia.load(modelo,serial);
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            dao.rollbackTransaction();
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
    }

    @Override
    public Modelo consultarModelo(String nombre) throws ExcepcionServicios {
        if(nombre.length()==0)throw new ExcepcionServicios(ExcepcionServicios.NOMBRE_INVALIDO);
        try {
            dao.beginSession();
            complejoPersistencia=dao.getDaoEquipoComplejo();
            Modelo ans=complejoPersistencia.loadModelo(nombre);
            dao.commitTransaction();
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
    }

    @Override
    public List<String> consultarAproximado(String cadena) throws ExcepcionServicios{
        if(cadena.length()==0)throw new ExcepcionServicios("Cadena de longitud invalida");
        dao.beginSession();
        complejoPersistencia=dao.getDaoEquipoComplejo();
        List<String> ans=complejoPersistencia.loadModelosAproximados(cadena);
        dao.commitTransaction();
        dao.endSession();
        return ans;
    }

}
