/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis.mappers;

import eci.pdsw.entities.EquipoSencillo;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author 2107803
 */
public interface EquipoSencilloMapper {
   
   public void insertEquipo(@Param("EqSencillo") EquipoSencillo es);
   
   public EquipoSencillo loadEquipoByNombre(@Param("Eq_Nombre") String nombre);
   
   public void update(@Param("Eq_Viejo") EquipoSencillo eqv, @Param("Eq_Nuevo") EquipoSencillo eqn);
   
   public ArrayList<EquipoSencillo> loadAll();
   
   public int loadEquipoByNombreDisponibilidad(@Param("Eq_Nombre") String nombre);
}
