/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.entities;

/**
 *
 * @author German Lopez
 */
public class EquipoException extends Exception {

    //Equipo complejo
    public static final String EQUIPOC_SIN_MODELO = "El equipo no puede ser registrado sin un modelo";
    public static final String EQUIPOC_SIN_SERIAL = "Favor colocarle un serial adecuado al equipo";
    public static final String EQUIPOC_SIN_MARCA = "Favor colocarle una marca adecuada al equipo";
    public static final String EUIPOC_SIN_PLACA = "Favor colocarle una placa adecuada al equipo";
    public static final String EUIPOC_SIN_ORDEN_DE_COMPRA = "Favor colocarle una orden de compra adecuada al equipo";
    //Modelo
    public static final String MODELO_SIN_NOMBRE = "Favor colocar un nombre al modelo";
    public static final String MODELO_SIN_CLASE = "Favor colocar una clase al modelo";
    public static final String MODELO_VIDA_UTIL_INADECUADA = "Favor colocar un valor de vida util adecuado al modelo";
    public static final String MODELO_VALOR_COMERCIAL_INADECUADO = "Favor colocar un valor comercial adecuado al modelo";
    //Equipo sencillo
    public static final String EQUIPO_S_SIN_NOMBRE = "El equipo no puede ser registrado sin un nombre";
    public static final String EQUIPO_S_SIN_CLASE = "Favor colocarle una clase adecuada al equipo";
    public static final String EQUIPO_S_CANTIDAD_INADECUADA = "Favor colocarle una cantidad adecuada del equipo";
    public static final String EQUIPO_S_COMERCIAL_INADECUADO = "Favor colocarle un valor comercial adecuado al equipo";

    public EquipoException(String message) {
        super(message);
    }

    public EquipoException(String message, Throwable cause) {
        super(message, cause);
    }

    public EquipoException(Throwable cause) {
        super(cause);
    }

    public EquipoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
