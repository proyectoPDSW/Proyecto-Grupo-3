/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.Persona;

/**
 *
 * @author David Useche
 */
public abstract class ServiciosPersona {

    private static final ServiciosPersona instance=new ServiciosPersonaPersistence();

    public static ServiciosPersona getInstance() throws RuntimeException{        
        return instance;
    }
    
    /**
    *Obtiene una persona a partir de su carnet
    * @param carnet de la persona
    * @return persona que tiene ese carnet
    */
    public abstract Persona personaCarnet(String carnet) throws ExcepcionServicios;
}
