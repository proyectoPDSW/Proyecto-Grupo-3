/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis;

import eci.pdsw.entities.Persona;
import eci.pdsw.persistence.DAOPersona;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo Alvarez
 */
public class MyBatisDAOPersona implements DAOPersona{
    private SqlSession ses;
    private PersonaMapper pmap;

    public MyBatisDAOPersona(SqlSession ses) {
        this.ses = ses;
        pmap = this.ses.getMapper(PersonaMapper.class);
    }
    
    

    @Override
    public Persona load(String carne) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Persona persona) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Persona persona) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
