/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.persistence.DAOPrestamo;
import eci.pdsw.persistence.PersistenceException;
import eci.pdsw.persistence.mybatis.mappers.PrestamoMapper;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Hugo Alvarez
 */
public class MyBatisDAOPrestamo  implements DAOPrestamo{
    private SqlSession ses;
    private PrestamoMapper pmap;

    public MyBatisDAOPrestamo(SqlSession ses) {
        this.ses = ses;
        pmap = this.ses.getMapper(PrestamoMapper.class);
    }
    @Override
    public List<Prestamo> load(Timestamp fecha, String carne) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Prestamo prestamo) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Prestamo prestamo) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadByFecha(Timestamp fecha) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadByCarne(String nombre) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadMorosos() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestamo> loadByEquipoComplejo(EquipoComplejo equipocomplejo) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
