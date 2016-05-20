/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence;

/**
 *
 * @author David Useche
 */
public class PersistenceException extends Exception {
    
    public final String EQUIPO_SIN_ORDEN_COMPRA="El equipo no tiene orden de compra";
    public final String NO_EXISTE_EQUIPO="No existe un equipo registrado con ese serial";

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
