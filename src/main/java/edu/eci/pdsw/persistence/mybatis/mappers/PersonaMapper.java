/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Rol;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Hugo Alvarez
 */
public interface PersonaMapper {
    
    public void insertPersona(@Param("Persona") Persona p);
    
    public void insertSeguridad(@Param("Persona") String carne,@Param("Rol") Rol rol);
    
    public void insertRol(@Param("rol") String r);
    
    public List<Persona> loadAll();
    
    public void update(@Param("Per_viejo") Persona pv,@Param("Per_nuevo") Persona pn);
    
    public Persona load(@Param("carne") String carne);
    
    public Persona loadPersRoles(@Param("carne") String carne);
    
    public List<Rol> loadRoles(@Param("carne") String carne);
    
    public List<Rol> loadAllRoles();
}
