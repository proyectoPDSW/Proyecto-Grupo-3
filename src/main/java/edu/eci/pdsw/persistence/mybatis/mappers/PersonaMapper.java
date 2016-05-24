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

    /**
     * Inserta una persona
     *
     * @param p persona a insertar
     */
    public void insertPersona(@Param("Persona") Persona p);

    /**
     * Inserta seguridad
     *
     * @param carne de la persona
     * @param rol de la persona
     */
    public void insertSeguridad(@Param("Persona") String carne, @Param("Rol") Rol rol);

    /**
     * Inserta un rol
     *
     * @param r rol a insertar
     */
    public void insertRol(@Param("rol") String r);

    /**
     * Carga todas las personas
     *
     * @return todas las personas
     */
    public List<Persona> loadAll();

    /**
     * Actualiza una persona
     *
     * @param pv persona a actualizar
     * @param pn persona actualizada
     */
    public void update(@Param("Per_viejo") Persona pv, @Param("Per_nuevo") Persona pn);

    /**
     * Carga una persona por carnet
     *
     * @param carne de la persona
     * @return la persona con carnet carne
     */
    public Persona load(@Param("carne") String carne);

    /**
     *
     * @param carne de la persona
     * @return la persona con carnet carne
     */
    public Persona loadPersRoles(@Param("carne") String carne);

    /**
     * Carga los roles de una persona
     *
     * @param carne de la persona
     * @return los roles de esa persona
     */
    public List<Rol> loadRoles(@Param("carne") String carne);

    /**
     * Carga todos los roles
     *
     * @return todos los roles
     */
    public List<Rol> loadAllRoles();
    
    /**
     * Calcula la cantidad de prestamos que ha tenido en mora la persona
     * 
     * @param per, el carnet de la persona de la que se quiere conocer la cantidad de prestamos morosos que ha tenido
     * @return la cantidad de prestamos que ha tenido en mora la persona
     */
    public int cantidadMorosos(@Param("persona") String per);
}
