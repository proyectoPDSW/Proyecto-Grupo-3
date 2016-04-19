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
    public EquipoComplejo load(String serial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EquipoComplejo load(int placa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(EquipoComplejo toSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EquipoComplejo toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reemplazar(EquipoComplejo old, EquipoComplejo novo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EquipoComplejo> loadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EquipoComplejo> loadDisponibles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EquipoComplejo> loadByModelo(String modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
