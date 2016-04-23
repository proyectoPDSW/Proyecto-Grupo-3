/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.servicios;

import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.persistence.DAOEquipoSencillo;
import eci.pdsw.persistence.DAOFactory;
import eci.pdsw.persistence.PersistenceException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2107803
 */
public class ServiciosEquipoSencilloPersistence extends ServiciosEquipoSencillo {

    private final DAOFactory dao;
    private DAOEquipoSencillo sencilloPersistencia;

    public ServiciosEquipoSencilloPersistence() {
        InputStream input;
        Properties properties = new Properties();
        try {
            input=ServiciosEquipoSencilloPersistence.class.getClassLoader().getResource("applicationconfig.properties").openStream();
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(ServiciosEquipoSencilloPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = DAOFactory.getInstance(properties);
    }

    @Override
    public void registrarEquipoSencillo(EquipoSencillo equipo) throws ExcepcionServicios {
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            sencilloPersistencia.save(equipo);
            dao.commitTransaction();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public EquipoSencillo consultarPorNombre(String nombre) throws ExcepcionServicios {
        EquipoSencillo ans;
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            ans = sencilloPersistencia.load(nombre);
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public List<EquipoSencillo> consultarTodo() throws ExcepcionServicios {
        ArrayList<EquipoSencillo> ans = new ArrayList<>();
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            ans = sencilloPersistencia.loadAll();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
        return ans;
    }

    @Override
    public int ConsultarDisponibilidadPorNombre(String nombre) throws ExcepcionServicios {
        int ans;
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            ans = sencilloPersistencia.loadByNombreDisponibles(nombre);
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

    @Override
    public void actualizar(EquipoSencillo equipo) throws ExcepcionServicios {
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            sencilloPersistencia.update(equipo);
            dao.commitTransaction();
            dao.endSession();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex.getMessage());
        }
    }

}
