/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis.mappers;

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.Modelo;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2107803
 */
public interface EquipoComplejoMapper {
    
    public void insertEquipo(@Param("EqComplejo") EquipoComplejo ec);
    
    public void insertModelo(@Param("Modelo")Modelo m);
    
    public ArrayList<EquipoComplejo> loadDisponibles();
    
    public EquipoComplejo loadEquipoBySerial(@Param("Eq_Serial") String serial);
    
    public EquipoComplejo loadEquipoByPlaca(@Param("Eq_Placa") long placa);
    
    public void update(@Param("Eq_Viejo") EquipoComplejo eqv,@Param("Eq_Nuevo") EquipoComplejo eqn);
    
    public void delete(@Param("Eq_Complejo") EquipoComplejo ec);
    
    public ArrayList<EquipoComplejo> loadAll();
    
    public ArrayList<EquipoComplejo> loadEquipoByModelo(@Param("Eq_Modelo") String modelo);
}
