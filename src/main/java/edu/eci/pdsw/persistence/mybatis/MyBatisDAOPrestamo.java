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
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo Alvarez
 */
public class MyBatisDAOPrestamo implements DAOPrestamo {

    private final SqlSession ses;
    private final PersonaMapper ppmp;
    private final PrestamoMapper pmap;
    private final EquipoComplejoMapper ecmp;
    private final EquipoSencilloMapper esmp;

    public MyBatisDAOPrestamo(SqlSession ses) {
        this.ses = ses;
        pmap = this.ses.getMapper(PrestamoMapper.class);
        ppmp = this.ses.getMapper(PersonaMapper.class);
        ecmp = this.ses.getMapper(EquipoComplejoMapper.class);
        esmp = this.ses.getMapper(EquipoSencilloMapper.class);
    }

    @Override
    public Prestamo load(Timestamp fecha, String carne) throws PersistenceException {
        if (carne == null) {
            throw new PersistenceException("El carnet no puede ser nulo");
        }
        if (fecha == null) {
            throw new PersistenceException("La fecha no puede ser nulo");
        }
        return pmap.loadPrestamo(fecha, carne);
    }

    @Override
    public void save(Prestamo prestamo) throws PersistenceException {
        if (prestamo == null) {
            throw new PersistenceException("El prestamo no puede ser nulo");
        }
        if ((prestamo.getEquiposComplejosPrestados() == null || prestamo.getEquiposComplejosPrestados().isEmpty()) && (prestamo.getEquiposSencillosPrestados() == null || prestamo.getEquiposSencillosPrestados().isEmpty())) {
            throw new PersistenceException("Los equipos no pueden ser nulos");
        }
        if (prestamo.getElQuePideElPrestamo() == null) {
            throw new PersistenceException("La persona no puede ser nulo");
        }
        Prestamo lisp = load(prestamo.getFechaInicio(), prestamo.getElQuePideElPrestamo().getCarnet());
        if (lisp != null && lisp.equals(prestamo)) {
            throw new PersistenceException("El prestamo ya existe");
        }
        if (ppmp.load(prestamo.getElQuePideElPrestamo().getCarnet()) == null) {
            throw new PersistenceException("La persona no existe para poder realizar el prestamo");
        }
        pmap.insertPrestamo(prestamo);
        if (prestamo.getEquiposComplejosPrestados() != null) {
            for (EquipoComplejo lisp1 : prestamo.getEquiposComplejosPrestados()) {
                lisp1.setDisponibilidad(false);
                lisp1.setEstado(prestamo.getTipo_prestamo());
                ecmp.update(lisp1, lisp1);
                pmap.insertEquipoComplejo_Prestamo(prestamo, lisp1);
            }

        }
        if (prestamo.getEquiposSencillosPrestados() != null) {
            for (EquipoSencillo p : prestamo.getEquiposSencillosPrestados()) {
                //if(esmp.loadEquipoByNombre(p.getNombre())==null) throw new PersistenceException("El equipo sencillo no existe para poder realizar el prestamo");
                pmap.insertEquipoSencillo_Prestamo(prestamo, p);
            }
        }
    }

    @Override
    public void update(Prestamo prestamo) throws PersistenceException {
        Prestamo viejo=load(prestamo.getFechaInicio(), prestamo.getElQuePideElPrestamo().getCarnet());
        if (viejo == null) {
            throw new PersistenceException("El prestamo no existe así que no se puede actualizar");
        }
        //Prestamo pres=load(prestamo.getFechaInicio(), prestamo.getElQuePideElPrestamo().getCarnet());
        //if(!prestamo.terminado()) throw new PersistenceException("el prestamo no puede ser actualizado pues ya ha terminado");
        if (prestamo.terminado()) {
            pmap.updatePrestamo(prestamo);
        }
        for (EquipoComplejo ec: prestamo.getEquiposComplejosPrestados()){
            if(!viejo.getEquiposComplejosPrestados().contains(ec)){
                pmap.insertEquipoComplejo_Prestamo(prestamo, ec);
            }
            if(!prestamo.isFaltante(ec)){
                pmap.updateEquipoComplejo(prestamo, ec);
            }
        }
        for (EquipoSencillo es : prestamo.getEquiposSencillosPrestados()) {
            if(!viejo.getEquiposSencillosPrestados().contains(es)){
                pmap.insertEquipoSencillo_Prestamo(prestamo, es);
            }
            pmap.updateEquipoSencillo(prestamo, es);
        }
        
    }

    @Override
    public List<Prestamo> loadAll() throws PersistenceException {
        return pmap.loadAll();
    }

    @Override
    public List<Prestamo> loadByFecha(Timestamp fecha) throws PersistenceException {
        if (fecha == null) {
            throw new PersistenceException("La fecha no puede ser nulo");
        }
        return pmap.loadByFecha(fecha);
    }

    @Override
    public List<Prestamo> loadByCarne(String carne) throws PersistenceException {
        if (carne == null) {
            throw new PersistenceException("El carnet no puede ser nulo");
        }
        return pmap.loadByCarne(carne);
    }

    @Override
    public List<Prestamo> loadMorosos() throws PersistenceException {
        //System.out.println(Arrays.toString(pmap.loadMorosos().toArray()));
        return pmap.loadMorosos();
    }

    @Override
    public List<Prestamo> loadByEquipoComplejo(EquipoComplejo equipocomplejo) throws PersistenceException {
        if (equipocomplejo == null) {
            throw new PersistenceException("El equipo complejo no puede ser nulo");
        }
        return pmap.loadByEquipoComplejo(equipocomplejo);
    }

    @Override
    public Timestamp currDate() {
        return new Timestamp(pmap.loadCurrDate().getTime());
    }

    @Override
    public Prestamo loadPrestamoActual(EquipoComplejo equipo) throws PersistenceException {
        if (pmap.loadPrestamoActualEquipo(equipo) == null) {
            throw new PersistenceException("No hay prestamo activo");
        }
        return pmap.loadPrestamoActualEquipo(equipo);
    }

}
