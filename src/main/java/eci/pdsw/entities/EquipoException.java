/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.entities;

/**
 *
 * @author german
 */
public class EquipoException extends Exception{
    //Equipo complejo
    public static final String EQUIPOC_SIN_MODELO="El equipo no puede ser registrado sin un modelo";
    public static final String EQUIPOC_SIN_SERIAL="Favor coloarle un serial adecuado al equipo";
    public static final String EQUIPOC_SIN_MARCA="Favor colocarle una marca adecuada al equipo";
    //Modelo
    public static final String MODELO_SIN_NOMBRE="Favor coloacar un nombre al modelo";
    public static final String MODELO_SIN_CLASE="Favor colocar una clase al modelo";
    public static final String MODELO_VIDA_UTIL_NEGATVA="Favor colocar un valor de vida util adecuado al modelo";
    public static final String MODELO_VALOR_COMPRA_NEGATVO="Favor colocar un valor de compra adecuado al modelo";
    //Equipo sencillo
    public static final String EQUIPO_S_SIN_NOMBRE="El equipo no puede ser registrado sin un nombre";
    public static final String EQUIPO_S_SIN_CLASE="Favor coloarle una clase adecuada al equipo";
    public static final String EQUIPO_S_CANTIDAD_NEGATIVA="Favor colocarle una cantidad adecuada del equipo";
    public static final String EQUIPO_S_COMPRA_NEGATIVO="Favor colocarle un valor de compra adecuado al equipo";
    
    public EquipoException(String message){
        super(message);
    }
}
