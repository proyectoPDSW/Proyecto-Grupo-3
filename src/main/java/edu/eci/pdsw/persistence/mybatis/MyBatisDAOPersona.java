/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
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
        if(carne==null) throw new PersistenceException("El carnet no puede ser nula");
        return pmap.load(carne);
    }

    @Override
    public void save(Persona persona) throws PersistenceException {
        if(persona==null) throw new PersistenceException("La Persona no puede ser nula");
        if(load(persona.getCarnet())!=null){
            if(persona.equals(load(persona.getCarnet()))) throw new PersistenceException("La Persona "+persona.getNombre()+" ya existe en la base de datos");
        }
        pmap.insertPersona(persona);
        System.out.println("Inserto Bien -sa-s-ad-as-d-asd-sa-dsa-");
    }

    @Override
    public void update(Persona persona) throws PersistenceException {
        if(persona==null) throw new PersistenceException("La Persona no puede ser nula");
        Persona anterior = load(persona.getCarnet());
        if(!anterior.toString().equals(persona.toString())) pmap.update(anterior, persona);
    }

    @Override
    public List<Persona> loadAll() throws PersistenceException {
        return pmap.loadAll();
    }
    
}
