/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.servicios;

import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.log.Registro;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Useche
 */
class ServiciosPersonaPersistence extends ServiciosPersona {

    private DAOFactory daoF;
    private DAOPersona basePersona;
    
    public ServiciosPersonaPersistence() {
    	try {
            
            InputStream input = getClass().getClassLoader().getResource("applicationconfig.properties").openStream();
            
            Properties properties=new Properties();
            properties.load(input);
            daoF = DAOFactory.getInstance(properties);
          
        } catch (IOException ex) {
            Registro.anotar(ex);
        }        
    }

    @Override
    public Persona personaCarnet(String carnet) throws ExcepcionServicios {
        Persona p;
        try{
            daoF.beginSession();
            basePersona=daoF.getDaoPersona();
            p=basePersona.load(carnet);
        }catch(PersistenceException e){
            throw new ExcepcionServicios(e,e.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
        return p;
    }

    @Override
    public int calcMoras(String carnet) throws ExcepcionServicios {
        int moras;
        try{
            daoF.beginSession();
            basePersona=daoF.getDaoPersona();
            moras=basePersona.calcMorasPrev(carnet);
            daoF.commitTransaction();
        } catch (PersistenceException ex) {
            throw new ExcepcionServicios(ex, ex.getLocalizedMessage());
        }finally{
            daoF.endSession();
        }
        return moras; 
    }
    
}
