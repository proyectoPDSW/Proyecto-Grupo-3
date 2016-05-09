/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

/**
 *
 * @author David Useche
 */
public class PrestamoException extends Exception {

    public static final String OBJETO_COMPLEJO_NO_ENCONTRADO = "No se encontro el objeto en la lista por devolver";
    public static final String OBJETO_SENCILLO_NO_ENCONTRADO = "El equipo sencillo no es faltante";

    public PrestamoException(String message) {
        super(message);
    }

    public PrestamoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrestamoException(Throwable cause) {
        super(cause);
    }

    public PrestamoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
