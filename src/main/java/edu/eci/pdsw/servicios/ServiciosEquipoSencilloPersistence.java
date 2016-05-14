/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Useche
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
            Registro.anotar(ex);
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
        return ans;
    }

    @Override
    public EquipoSencillo ConsultarDisponibilidadPorNombre(String nombre) throws ExcepcionServicios {
        EquipoSencillo ans=null;
        try {
            dao.beginSession();
            sencilloPersistencia = dao.getDaoEquipoSencillo();
            ans = sencilloPersistencia.loadByNombreDisponibles(nombre);
            dao.endSession();
            return ans;
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
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
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
    }

    @Override
    public int consultarCantidadDisponibleEqSencillo(String nombre) throws ExcepcionServicios {
        int ans=0;
        try{
            dao.beginSession();
            sencilloPersistencia= dao.getDaoEquipoSencillo();
            ans=sencilloPersistencia.loadCantidadDisponibleEquipoSencillo(nombre);
            dao.commitTransaction();
            dao.endSession();
        }catch(PersistenceException ex){
            throw new ExcepcionServicios(ex,ex.getLocalizedMessage());
        }
        return ans;
    }

    @Override
    public List<String> consultarPorNombreAproximado(String busqueda) throws ExcepcionServicios{
        if(busqueda.length()==0)throw new ExcepcionServicios("Longitud de cadena invalida");
        dao.beginSession();
        sencilloPersistencia=dao.getDaoEquipoSencillo();
        List<String> ans=sencilloPersistencia.loadAproximadamente(busqueda);
        dao.endSession();
        return ans;
    }

}
