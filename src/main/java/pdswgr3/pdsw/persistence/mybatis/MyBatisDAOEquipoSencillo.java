/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdswgr3.pdsw.persistence.mybatis;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import pdswgr3.pdsw.entities.EquipoSencillo;
import pdswgr3.pdsw.persistence.DAOEquipoSencillo;
import pdswgr3.pdsw.persistence.mybatis.mappers.EquipoSencilloMapper;

/**
 *
 * @author 2107803
 */
public class MyBatisDAOEquipoSencillo implements DAOEquipoSencillo {

    private SqlSession ses;
    private EquipoSencilloMapper eMap;
    
    public MyBatisDAOEquipoSencillo(SqlSession sesion) {
        ses=sesion;
        eMap=ses.getMapper(EquipoSencilloMapper.class);
    }

    @Override
    public EquipoSencillo load(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(EquipoSencillo toSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(EquipoSencillo toUpdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EquipoSencillo> loadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
