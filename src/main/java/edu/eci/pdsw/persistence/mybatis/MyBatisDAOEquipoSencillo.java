/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.EquipoSencilloMapper;
import java.util.List;

/**
 *
 * @author German Lopez
 */
public class MyBatisDAOEquipoSencillo implements DAOEquipoSencillo {

    private SqlSession ses;
    private EquipoSencilloMapper eMap;

    public MyBatisDAOEquipoSencillo(SqlSession sesion) {
        ses = sesion;
        eMap = ses.getMapper(EquipoSencilloMapper.class);
    }

    @Override
    public EquipoSencillo load(String nombre) throws PersistenceException {
        if (eMap.loadEquipoByNombre(nombre) == null) {
            throw new PersistenceException("El equipo con nombre " + nombre + " no esta registrado");
        }
        return eMap.loadEquipoByNombre(nombre);
    }

    @Override
    public void save(EquipoSencillo toSave) throws PersistenceException {
        if (eMap.loadEquipoByNombre(toSave.getNombre()) != null) {
            throw new PersistenceException("El equipo con nombre " + toSave.getNombre() + " ya esta registrado");
        }
        eMap.insertEquipo(toSave);
    }

    @Override
    public void update(EquipoSencillo toUpdate) throws PersistenceException {
        if (eMap.loadEquipoByNombre(toUpdate.getNombre()) == null) {
            throw new PersistenceException("El equipo con nombre " + toUpdate.getNombre() + " a actualizar no esta registrado");
        }
        EquipoSencillo test = eMap.loadEquipoByNombre(toUpdate.getNombre());
        if (!test.toString().equals(toUpdate.toString())) {
            eMap.update(test, toUpdate);
        }
    }

    @Override
    public ArrayList<EquipoSencillo> loadAll() throws PersistenceException {
        return eMap.loadAll();
    }

    @Override
    public EquipoSencillo loadByNombreDisponibles(String nombre) throws PersistenceException {
        EquipoSencillo es;
        if (eMap.loadEquipoByNombre(nombre) == null) {
            throw new PersistenceException("El equipo con nombre " + nombre + " no esta registrado");
        }
        es=eMap.loadEquipoByNombreDisponibilidad(nombre);
        if(es==null){
            throw new PersistenceException("La herramienta con nombre "+ nombre +" no esta registrada");
        }
        return es;
    }

    @Override
    public int loadCantidadDisponibleEquipoSencillo(String nombre) throws PersistenceException {
        if (nombre.length() == 0) {
            throw new PersistenceException("Favor colocar un nombre valido");
        }
        if(eMap.consultarEquipoSencilloCantidadDisponible(nombre) ==null)
            return eMap.loadEquipoByNombre(nombre).getCantidadTotal();
        else
            return eMap.consultarEquipoSencilloCantidadDisponible(nombre);
    }

    @Override
    public List<String> loadAproximadamente(String nombre) {
        nombre=nombre.trim();
        return eMap.loadHerramientaAproximada(nombre);
    }

}
