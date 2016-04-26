/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.persistence.DAOPrestamo;
import edu.eci.pdsw.persistence.PersistenceException;
import edu.eci.pdsw.persistence.mybatis.mappers.EquipoComplejoMapper;
import edu.eci.pdsw.persistence.mybatis.mappers.EquipoSencilloMapper;
import edu.eci.pdsw.persistence.mybatis.mappers.PersonaMapper;
import edu.eci.pdsw.persistence.mybatis.mappers.PrestamoMapper;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
        /*if(pmap.loadPrestamo(fecha,carne).isEmpty()) 
            throw new PersistenceException("no existe ningun Prestamo en la base de datos con la fecha "+fecha.toString()+" y el carnet "+carne);*/
        return pmap.loadPrestamo(fecha,carne);
    }

    @Override
    public void save(Prestamo prestamo) throws PersistenceException {
        if(prestamo==null) throw new PersistenceException("El prestamo no puede ser nulo");
        if(prestamo.getEquiposComplejosPrestados()== null && prestamo.getEquiposSencillosPrestados()==null) throw new PersistenceException("Los equipos no pueden ser nulos");
        if(prestamo.getElQuePideElPrestamo()==null)throw new PersistenceException("La persona no puede ser nulo");
        List<Prestamo> lisp = load(prestamo.getFechaInicio(),prestamo.getElQuePideElPrestamo().getCarnet());
        //System.out.println(lisp.size()+" LONGITUD");
        for (Prestamo prestamo1 : lisp) if(prestamo1.equals(prestamo)) throw new PersistenceException("El prestamo ya existe");
        //System.out.println("------------>>>>>>"+ppmp.load(prestamo.getElQuePideElPrestamo().getCarnet()));
        if(ppmp.load(prestamo.getElQuePideElPrestamo().getCarnet())==null) throw new PersistenceException("La persona no existe para poder realizar el prestamo");
        pmap.insertPrestamo(prestamo);
        Prestamo car_prestamo= null;
        int id = -1;
        List<Prestamo> pt = pmap.loadPrestamo(prestamo.getFechaInicio(), prestamo.getElQuePideElPrestamo().getCarnet());
        System.out.println(Arrays.toString(pt.toArray()));
        for (Prestamo pres : pt) {
            if(pres.equals(prestamo)) car_prestamo=pres;
        }
        System.out.println("LLEGOOOOOOOOOOOOOO!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(car_prestamo);
        System.out.println("sdaasdasdsadasdasdasdasdasd!!!!!!!!!!!!!!!!!!!!!");
        if(prestamo.getEquiposComplejosPrestados()!=null){
            for (EquipoComplejo ec : prestamo.getEquiposComplejosPrestados()) {
                if(ecmp.loadEquipoBySerial(ec.getSerial())==null) throw new PersistenceException("El equipo complejo no existe para poder realizar el prestamo");
                EquipoComplejo eqc2 = ecmp.loadEquipoByPlaca(ec.getPlaca());
                pmap.insertEquipoComplejo_Prestamo(car_prestamo.getIdPrestamo(), eqc2.getId_Eq());
            }
        }
        if(prestamo.getEquiposSencillosPrestados()!=null){
            for (EquipoSencillo p : prestamo.getEquiposSencillosPrestados().keySet()) {
                if(esmp.loadEquipoByNombre(p.getNombre())==null) throw new PersistenceException("El equipo sencillo no existe para poder realizar el prestamo");
                pmap.insertEquipoSencillo_Prestamo(car_prestamo.getIdPrestamo(), p.getNombre(), p.getCantidadTotal());
            }
        }
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
