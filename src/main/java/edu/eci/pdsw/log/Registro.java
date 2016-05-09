/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.log;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;
import java.lang.*;

/**
 *
 * @author Daniela Sepulveda
 */
public class Registro {
    public static String nombre="labelectronica";

    /**
     * Envia los errores a un archivo .log
     * @param e , excepcion que se presenta en la ejecucion del programa
     */
    
    public static void anotar(Exception e) {
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace(); 
        }
    }
    
}
   
