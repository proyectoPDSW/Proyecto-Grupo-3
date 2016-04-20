/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.persistence.DAOEquipoComplejo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.persistence.mybatis.mappers.EquipoComplejoMapper;

/**
 *
 * @author 2107803
 */
public class MybatisDAOEquipoComplejo implements DAOEquipoComplejo {

    private SqlSession ses;
    private EquipoComplejoMapper eMap;
    
    public MybatisDAOEquipoComplejo(SqlSession sesion) {
        ses=sesion;
        eMap=ses.getMapper(EquipoComplejoMapper.class);
    }

    @Override
    public EquipoComplejo load(String serial) throws PersistenceException {
        if(eMap.loadEquipoBySerial(serial)==null) throw new PersistenceException("El equipo con serial "+serial+" no esta registrado");
        return eMap.loadEquipoBySerial(serial);
    }

    @Override
    public EquipoComplejo load(int placa) throws PersistenceException  {
        if(eMap.loadEquipoByPlaca(placa)==null) throw new PersistenceException("El equipo con serial "+placa+" no esta registrado");
        return eMap.loadEquipoByPlaca(placa);
    }

    @Override
    public void save(EquipoComplejo toSave)throws PersistenceException  {
       if(eMap.loadEquipoByPlaca(toSave.getPlaca())!=null & eMap.loadEquipoBySerial(toSave.getSerial())!=null) throw new PersistenceException("El equipo con nombre "+toSave.getModelo_Eq().getNombre()+" ya esta registrado");
       eMap.insertEquipo(toSave);
    }

    @Override
    public void update(EquipoComplejo toUpdate)throws PersistenceException  {
        if(eMap.loadEquipoByPlaca(toUpdate.getPlaca())==null & eMap.loadEquipoBySerial(toUpdate.getSerial())==null) throw new PersistenceException("El equipo con nombre "+toUpdate.getModelo_Eq().getNombre()+" no esta registrado");
        EquipoComplejo test = null;
        if(toUpdate.getSerial()==null){
            test=eMap.loadEquipoByPlaca(toUpdate.getPlaca());
        }else if(toUpdate.getPlaca()==0){
            test=eMap.loadEquipoBySerial(toUpdate.getSerial());
        }
        
        if(test.toString()!=toUpdate.toString()){
            eMap.update(test, toUpdate);
        }
    }

    //SOBRARIA!!!!!!
    @Override
    public void reemplazar(EquipoComplejo old, EquipoComplejo novo)throws PersistenceException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EquipoComplejo> loadAll()throws PersistenceException  {
        return eMap.loadAll();
    }

    @Override
    public ArrayList<EquipoComplejo> loadDisponibles()throws PersistenceException  {
        return eMap.loadDisponibles();
    }

    @Override
    public ArrayList<EquipoComplejo> loadByModelo(String modelo)throws PersistenceException  {
        if(eMap.loadEquipoByModelo(modelo)==null) throw new PersistenceException("El modelo "+modelo+" no esta registrado");
        return eMap.loadEquipoByModelo(modelo);
    }

}
