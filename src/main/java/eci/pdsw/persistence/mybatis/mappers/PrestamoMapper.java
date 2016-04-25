/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis.mappers;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Hugo Alvarez
 */
public interface PrestamoMapper {
    
    
    //public void insertPrestamo(@Param("Prestamo")Prestamo p);
    
    //public void insertEquipoComplejo_Prestamo(@Param("Prestamo_id")int p, @Param("Equipo_id")int e);
    
    //public void insertEquipoSencillo_Prestamo(@Param("Prestamo_id")int p, @Param("Equipo_id")String e, @Param("cantidad")int c);
    
    //public void updatePrestamo(@Param("Pre_viejo")Prestamo pv,@Param("Pre_nuevo")Prestamo pn);
    
    public List<Prestamo> loadPrestamo(@Param("time")Timestamp fecha,@Param("carnet") String carne);
    
    public List<Prestamo> loadAll();
    
    public List<Prestamo> loadByFecha(@Param("fecha")Timestamp fecha);

    public List<Prestamo> loadByCarne(@Param("carne")String carne);

    public List<Prestamo> loadMorosos();

    public List<Prestamo> loadByEquipoComplejo(@Param("Eq_Complejo")EquipoComplejo equipocomplejo);
}
