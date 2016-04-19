/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import java.util.Properties;

/**
 *
 * @author Zawsx
 */
public abstract class DAOFactory {
    private static DAOFactory instance=null;
    
    public static DAOFactory getInstance(Properties appProperties){
        return instance;
    }
    
    public abstract void beginSession();
    public abstract void commitTransaction();
    public abstract void rollbackTransaction();
    public abstract void endSession();
    public abstract DAOEquipoComplejo getDaoEquipoComplejo();
    public abstract DAOEquipoSencillo getDaoEquipoSencillo();
    public abstract DAOPersona getDaoPersona();
    
}
