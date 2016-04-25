/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.persistence.DAOPrestamo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.persistence.mybatis.mappers.EquipoComplejoMapper;
import eci.pdsw.persistence.mybatis.mappers.EquipoSencilloMapper;
import eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
import eci.pdsw.persistence.mybatis.mappers.PrestamoMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo Alvarez
 */
public class MyBatisDAOPrestamo  implements DAOPrestamo{
    private SqlSession ses;
    private PersonaMapper ppmp;
    private PrestamoMapper pmap;
    private EquipoComplejoMapper ecmp;
    private EquipoSencilloMapper esmp;
    public MyBatisDAOPrestamo(SqlSession ses) {
        this.ses = ses;
        pmap = this.ses.getMapper(PrestamoMapper.class);
        ppmp = this.ses.getMapper(PersonaMapper.class);
        ecmp = this.ses.getMapper(EquipoComplejoMapper.class);
        esmp = this.ses.getMapper(EquipoSencilloMapper.class);
    }
    @Override
    public List<Prestamo> load(Timestamp fecha, String carne) throws PersistenceException {
        if(carne==null) throw new PersistenceException("El carnet no puede ser nulo");
        if(fecha==null) throw new PersistenceException("La fecha no puede ser nulo");
        if(pmap.loadPrestamo(fecha,carne).isEmpty()) 
            throw new PersistenceException("no existe ningun Prestamo en la base de datos con la fecha "+fecha.toString()+" y el carnet "+carne);
        return pmap.loadPrestamo(fecha,carne);
    }

    @Override
    public void save(Prestamo prestamo) throws PersistenceException {
     /*   if(prestamo==null) throw new PersistenceException("El prestamo no puede ser nulo");
        List<Prestamo> lisp = load(prestamo.getFechaInicio(),prestamo.getElQuePideElPrestamo().getCarnet());
        for (Prestamo prestamo1 : lisp) if(prestamo1.equals(prestamo)) throw new PersistenceException("El prestamo ya existe ");
        if(ppmp.load(prestamo.getElQuePideElPrestamo().getCarnet())==null) throw new PersistenceException("La persona no existe para poder realizar el prestamo");
        pmap.insertPrestamo(prestamo);
        Prestamo car_prestamo= null;
        int id = -1;
        List<Prestamo> pt = pmap.loadPrestamo(prestamo.getFechaInicio(), prestamo.getElQuePideElPrestamo().getCarnet());
        for (Prestamo pres : pt) {
            if(pres.equals(prestamo)) car_prestamo=pres;
        }
        for (EquipoComplejo ec : car_prestamo.getEquiposComplejosPrestados()) {
            if(ecmp.loadEquipoBySerial(ec.getSerial())==null) throw new PersistenceException("El equipo complejo no existe para poder realizar el prestamo");
            pmap.insertEquipoComplejo_Prestamo(car_prestamo.getIdPrestamo(), ec.getId_Eq());
        }
        for (EquipoSencillo p : car_prestamo.getEquiposSencillosPrestados().keySet()) {
            if(esmp.loadEquipoByNombre(p.getNombre())==null) throw new PersistenceException("El equipo sencillo no existe para poder realizar el prestamo");
            pmap.insertEquipoSencillo_Prestamo(car_prestamo.getIdPrestamo(), p.getNombre(), p.getCantidadTotal());
        }
       */
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Prestamo prestamo) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadAll() throws PersistenceException {
        return pmap.loadAll();
    }

    @Override
    public List<Prestamo> loadByFecha(Timestamp fecha) throws PersistenceException {
        if(fecha==null) throw new PersistenceException("La fecha no puede ser nulo");
        return pmap.loadByFecha(fecha);
    }

    @Override
    public List<Prestamo> loadByCarne(String carne) throws PersistenceException {
        if(carne==null) throw new PersistenceException("El carnet no puede ser nulo");
        return pmap.loadByCarne(carne);
    }

    @Override
    public List<Prestamo> loadMorosos() throws PersistenceException {
        return pmap.loadMorosos();
    }

    @Override
    public List<Prestamo> loadByEquipoComplejo(EquipoComplejo equipocomplejo) throws PersistenceException {
        if(equipocomplejo==null) throw new PersistenceException("El equipo complejo no puede ser nulo");
        return pmap.loadByEquipoComplejo(equipocomplejo);
    }
    
}
