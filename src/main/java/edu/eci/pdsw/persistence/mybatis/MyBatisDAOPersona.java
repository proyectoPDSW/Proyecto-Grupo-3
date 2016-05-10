/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Rol;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo Alvarez
 */
public class MyBatisDAOPersona implements DAOPersona {

    private SqlSession ses;
    private PersonaMapper pmap;

    public MyBatisDAOPersona(SqlSession ses) {
        this.ses = ses;
        pmap = this.ses.getMapper(PersonaMapper.class);
    }

    @Override
    public Persona load(String carne) throws PersistenceException {
        if (carne == null || carne.length() <= 0) {
            throw new PersistenceException("El carnet no puede ser nula");
        }
        return pmap.load(carne);
    }

    @Override
    public void save(Persona persona) throws PersistenceException {
        if (persona == null) {
            throw new PersistenceException("La Persona no puede ser nula");
        }
        if (load(persona.getCarnet()) != null) {
            if (persona.equals(load(persona.getCarnet()))) {
                throw new PersistenceException("La Persona " + persona.getNombre() + " ya existe en la base de datos");
            }
        }
        pmap.insertPersona(persona);
    }

    @Override
    public void save(String carne, Rol r) throws PersistenceException {
        if (pmap.load(carne) == null) {
            throw new PersistenceException("La persona con carne " + carne + " no esta registrada en la base de datos");
        }
        if (r == null) {
            throw new PersistenceException("El rol no puede ser nulo");
        }
        if (pmap.loadRoles(carne).contains(r)) {
            throw new PersistenceException("La persona con carne " + carne + " ya posee el rol " + r.getRol());
        }
        pmap.insertSeguridad(carne, r);
    }

    @Override
    public void update(Persona persona) throws PersistenceException {
        if (persona == null) {
            throw new PersistenceException("La Persona no puede ser nula");
        }
        Persona anterior = load(persona.getCarnet());
        if (!anterior.toString().equals(persona.toString())) {
            pmap.update(anterior, persona);
        }
    }

    @Override
    public List<Persona> loadAll() throws PersistenceException {
        return pmap.loadAll();
    }

    @Override
    public List<Rol> loadRoles(String carne) throws PersistenceException {
        if (pmap.load(carne) == null) {
            throw new PersistenceException("La persona con carne " + carne + " no esta registrada en la base de datos");
        }
        return pmap.loadAllRoles();
    }

    @Override
    public void save(String rol) throws PersistenceException {
        List<Rol> roles = new ArrayList<>();
        roles = pmap.loadAllRoles();
        boolean esta = false;
        int i = 0;
        while (esta != true || i < roles.size()) {
            if (roles.get(i).getRol().equals(rol)) {
                esta = true;
            }
            i++;
        }
        if (esta) {
            throw new PersistenceException("El rol " + rol + " ya esta registrado");
        }
        pmap.insertRol(rol);
    }

    @Override
    public List<Rol> loadAllRoles() throws PersistenceException {
        return pmap.loadAllRoles();
    }

    @Override
    public Persona loadPersRoles(String carne) throws PersistenceException {
        if (pmap.load(carne) == null) {
            throw new PersistenceException("La persona con carne " + carne + " no esta registrada en la base de datos");
        }
        return pmap.loadPersRoles(carne);
    }

}
