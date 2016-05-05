/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Prestamo;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Hugo Alvarez
 */
public interface PrestamoMapper {
    
    
    public void insertPrestamo(@Param("Prestamo")Prestamo p);
    
    public void insertEquipoComplejo_Prestamo(@Param("Prestamo_id")Prestamo p, @Param("Equipo_id")EquipoComplejo e);
    
    public void insertEquipoSencillo_Prestamo(@Param("Prestamo_id")Prestamo p, @Param("Equipo_id")EquipoSencillo e);
    
    public void updatePrestamo(@Param("Prestamo")Prestamo pn);
    
    public void updateEquipoSencillo(@Param("Prestamo") Prestamo p,@Param("Eq_Sencillo") EquipoSencillo es);
    
    public List<Prestamo> loadPrestamo(@Param("time")Timestamp fecha,@Param("carnet") String carne);
    
    public List<Prestamo> loadAll();
    
    public List<Prestamo> loadByFecha(@Param("fecha")Timestamp fecha);

    public List<Prestamo> loadByCarne(@Param("carne")String carne);

    public List<Prestamo> loadMorosos();

    public List<Prestamo> loadByEquipoComplejo(@Param("Eq_Complejo")EquipoComplejo equipocomplejo);
    
    public List<Prestamo> loadByEquipoSencillo(@Param("Eq_Sencillo")EquipoSencillo equiposencillo);
}
