/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.EquipoComplejoMapper;
import java.util.List;

/**
 *
 * @author German Lopez
 */
public class MybatisDAOEquipoComplejo implements DAOEquipoComplejo {

    private SqlSession ses;
    private EquipoComplejoMapper eMap;

    public MybatisDAOEquipoComplejo(SqlSession sesion) {
        ses = sesion;
        eMap = ses.getMapper(EquipoComplejoMapper.class);
    }

    @Override
    public EquipoComplejo load(String modelo, String serial) throws PersistenceException {
        if (eMap.loadEquipoBySerial(modelo, serial) == null) {
            throw new PersistenceException("El equipo con serial " + serial + " no esta registrado");
        }
        //System.out.println(eMap.loadEquipoBySerial(modelo, serial));
        return eMap.loadEquipoBySerial(modelo, serial);
    }

    @Override
    public EquipoComplejo load(String placa) throws PersistenceException {
        if (eMap.loadEquipoByPlaca(placa) == null) {
            throw new PersistenceException("El equipo con placa " + placa + " no esta registrado");
        }
        //System.out.println(eMap.loadEquipoByPlaca(placa));
        return eMap.loadEquipoByPlaca(placa);
    }

    @Override
    public void save(EquipoComplejo toSave) throws PersistenceException {
        if (eMap.loadEquipoByPlaca(toSave.getPlaca()) != null) {
            throw new PersistenceException("El equipo con placa " + toSave.getPlaca() + " ya esta registrado");
        }
        if (eMap.loadEquipoBySerial(toSave.getModelo_Eq().getNombre(), toSave.getSerial()) != null) {
            throw new PersistenceException("El equipo con serial " + toSave.getSerial() + " ya esta registrado");
        }
        //Como si el modelo no esta registrado se registra automaticamente, tengo que ver si existe
        if (eMap.loadModelo(toSave.getModelo_Eq().getNombre()) == null) {
            //Si el modelo no existe lo registro
            save(toSave.getModelo_Eq());
        }
        eMap.insertEquipo(toSave);
    }

    @Override
    public void update(EquipoComplejo toUpdate) throws PersistenceException {
        if (eMap.loadEquipoByPlaca(toUpdate.getPlaca()) == null
                && eMap.loadEquipoBySerial(toUpdate.getModelo_Eq().getNombre(), toUpdate.getSerial()) == null) {
            throw new PersistenceException("El equipo con nombre " + toUpdate.getModelo_Eq().getNombre()
                    + " no esta registrado");
        }
        EquipoComplejo test = null;
        if (toUpdate.getSerial() == null || !toUpdate.getPlaca().equals("0")) {
            test = eMap.loadEquipoByPlaca(toUpdate.getPlaca());
        } else if (toUpdate.getPlaca().equals("0")) {
            test = eMap.loadEquipoBySerial(toUpdate.getModelo_Eq().getNombre(), toUpdate.getSerial());
        }
        if (test.equals(toUpdate)) {
            eMap.update(test, toUpdate);
        }
    }

    @Override
    public void save(Modelo model) throws PersistenceException {
        if (eMap.loadModelo(model.getNombre()) != null) {
            throw new PersistenceException("El modelo " + model.getNombre() + " ya esta registrado");
        }
        eMap.insertModelo(model);
    }

    @Override
    public Modelo loadModelo(String nombre) throws PersistenceException {
        if (nombre.length() == 0) {
            throw new PersistenceException("Favor colocar un modelo adecuado");
        }
        //if (eMap.loadModelo(nombre) == null) {
        // throw new PersistenceException("El modelo " + nombre + " no esta registrado");
        //}

        return eMap.loadModelo(nombre);
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
            throw new PersistenceException("El modelo " + modelo + " no esta registrado");
        }
        return eMap.loadEquipoByModelo(modelo);
    }

    @Override
    public List<String> loadModelosAproximados(String toSearch) {
        toSearch = toSearch.trim().toLowerCase();
        return eMap.loadModelosAproximados(toSearch);
    }

    @Override
    public ArrayList<EquipoComplejo> loadEnAlmacenByModelo(String modelo) throws PersistenceException {
        ArrayList<EquipoComplejo> modelos=eMap.loadEnAlmacenByModelo(modelo);
        if (modelos == null) {
            throw new PersistenceException("El modelo " + modelo + " no esta registrado");
        }
        return modelos;
    }
    @Override
    public OrdenCompra loadOrdenCompraBySerial(String serial) throws PersistenceException {
        if(serial==null || serial.length()==0) throw new PersistenceException("No se ha encontrado equipos registrados con ese serial");
        OrdenCompra orden=eMap.loadOrddenCompraBySerial(serial);
        if (orden==null) throw new PersistenceException("El equipo no tiene orden de compra");
        return orden;
    }
    @Override
    public EquipoComplejo loadEnAlmacenPorPlaca(String placa) throws PersistenceException {
        EquipoComplejo equipo=eMap.loadEquipoDisponibleByPlaca(placa);
        if(equipo==null){
            throw new PersistenceException("El equipo con placa "+ placa +" no esta registrado");
        }
        if(!equipo.getEstado().equals(EquipoComplejo.almacen)){
            throw new PersistenceException("El equipo con placa "+ placa +" no esta disponible para prestar");
        }
        return equipo;
    }
}
