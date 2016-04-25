/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis.mappers;

import edu.eci.pdsw.entities.Persona;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Hugo Alvarez
 */
public interface PersonaMapper {
    
    public void insertPersona(@Param("Persona") Persona p);
    
    public List<Persona> loadAll();
    
    public void update(@Param("Per_viejo") Persona pv,@Param("Per_nuevo") Persona pn);
    
    public Persona load(@Param("Carne") String carne);
}
