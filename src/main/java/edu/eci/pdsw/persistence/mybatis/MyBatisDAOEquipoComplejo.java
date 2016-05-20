/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.EquipoComplejoMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author 
 */
public class MyBatisDAOEquipoComplejo implements DAOEquipoComplejo {
    
    private SqlSession ses;
    private EquipoComplejoMapper eMap;

    public MyBatisDAOEquipoComplejo(SqlSession sesion) {
        ses = sesion;
        eMap = ses.getMapper(EquipoComplejoMapper.class);
    }

    @Override
    public EquipoComplejo load(String modelo, String serial) throws PersistenceException {
        EquipoComplejo eqComp=eMap.loadEquipoBySerial(modelo, serial);
        if (eqComp== null) {
            throw new PersistenceException("El equipo con serial " + serial + " no está registrado");
        }
        return eqComp;
    }

    @Override
    public EquipoComplejo load(String placa) throws PersistenceException {
        EquipoComplejo eqComp=eMap.loadEquipoByPlaca(placa);
        if (eqComp == null) {
            throw new PersistenceException("El equipo con placa " + placa + " no está registrado");
        }
        return eqComp;
    }

    @Override
    public void save(EquipoComplejo toSave) throws PersistenceException {
        if (eMap.loadEquipoByPlaca(toSave.getPlaca()) != null) {
            throw new PersistenceException("El equipo con placa " + toSave.getPlaca() + " ya está registrado");
        }
        if (eMap.loadEquipoBySerial(toSave.getModelo_Eq().getNombre(), toSave.getSerial()) != null) {
            throw new PersistenceException("El equipo con serial " + toSave.getSerial() + " ya está registrado");
        }
        //Como si el modelo no esta registrado se registra automaticamente, tengo que ver si existe
        if (eMap.loadModelo(toSave.getModelo_Eq().getNombre()) == null) {
            //Si el modelo no existe lo registro
            saveModelo(toSave.getModelo_Eq());
        }
        eMap.insertEquipo(toSave);
        saveOrdenCompra(toSave.getOrdenCompra(),toSave.getSerial(),toSave.getModelo_Eq()); 
    }

    @Override
    public void update(EquipoComplejo toUpdate) throws PersistenceException {
        EquipoComplejo old=eMap.loadEquipoBySerial(toUpdate.getModelo_Eq().getNombre(), toUpdate.getSerial());
        if (eMap.loadEquipoByPlaca(toUpdate.getPlaca()) == null && old == null) {
            throw new PersistenceException("El equipo con nombre " + toUpdate.getModelo_Eq().getNombre()+" no está registrado");
        }
        eMap.update(old, toUpdate);
    }

    @Override
    public ArrayList<EquipoComplejo> loadAll() throws PersistenceException {
        return eMap.loadAll();
    }

    @Override
    public ArrayList<EquipoComplejo> loadDisponibles() throws PersistenceException {
        return eMap.loadDisponibles();
    }

    @Override
    public ArrayList<EquipoComplejo> loadByModelo(String modelo) throws PersistenceException {
        if (eMap.loadModelo(modelo) == null) {
            throw new PersistenceException("El modelo " + modelo + " no está registrado");
        }
        return eMap.loadEquipoByModelo(modelo);
    }

    @Override
    public ArrayList<EquipoComplejo> loadEnAlmacenByModelo(String modelo) throws PersistenceException {
       if (modelo == null || modelo.length()==0 ) {
            throw new PersistenceException("El modelo " + modelo + " no está registrado");
        }
        ArrayList<EquipoComplejo> modelos=eMap.loadEnAlmacenByModelo(modelo);
        if (modelos == null) {
            throw new PersistenceException("No hay equipos registrados bajo el modelo en almacén: " + modelo);
        }
        return modelos;
    }

    @Override
    public void saveModelo(Modelo model) throws PersistenceException {
        if (eMap.loadModelo(model.getNombre()) != null) {
            throw new PersistenceException("El modelo " + model.getNombre() + " ya está registrado");
        }
        eMap.insertModelo(model);
    }

    @Override
    public void saveOrdenCompra(OrdenCompra ordenCompra,String serialEquipo, Modelo model) throws PersistenceException {
        if (model==null || eMap.loadModelo(model.getNombre()) == null) throw new PersistenceException("El modelo no está registrado");
        if(serialEquipo==null || serialEquipo.length()==0 || null==eMap.loadEquipoBySerial(model.getNombre(), serialEquipo)) throw new PersistenceException("No se ha encontrado equipos registrados con ese serial");
        eMap.insertOrdenCompra(ordenCompra, serialEquipo, model);   
    }

    @Override
    public Modelo loadModelo(String nombre) throws PersistenceException {
       if (nombre==null || nombre.length() == 0) {
            throw new PersistenceException("Favor colocar un modelo adecuado");
        }
        return eMap.loadModelo(nombre);
    }

    @Override
    public List<String> loadModelosAproximados(String toSearch) {
        toSearch = toSearch.trim().toLowerCase();
        return eMap.loadModelosAproximados(toSearch);
    }

    @Override
    public OrdenCompra loadOrdenCompraBySerial(String serial) throws PersistenceException {
        if(serial==null || serial.length()==0) throw new PersistenceException("No se ha encontrado equipos registrados con ese serial");
        OrdenCompra orden=eMap.loadOrdenCompraBySerial(serial);
        if (orden==null) throw new PersistenceException("El equipo no tiene orden de compra");
        return orden;
    }

    @Override
    public EquipoComplejo loadEnAlmacenPorPlaca(String placa) throws PersistenceException {
        EquipoComplejo equipo=eMap.loadEquipoDisponibleByPlaca(placa);
        if(equipo==null){
            throw new PersistenceException("El equipo con placa "+ placa +" no está registrado");
        }
        if(!equipo.getEstado().equals(EquipoComplejo.almacen)){
            throw new PersistenceException("El equipo con placa "+ placa +" no está disponible para prestar");
        }
        return equipo;
    }
    
}
