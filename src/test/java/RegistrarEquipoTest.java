/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Test;
import eci.pdsw.entities.Modelo;
import eci.pdsw.persistence.DAOEquipoComplejo;
import eci.pdsw.persistence.DAOEquipoSencillo;
import eci.pdsw.persistence.DAOFactory;
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;


/**
 *
 * @author amoto
 */
public class RegistrarEquipoTest {
    public static InputStream input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
    public static Properties properties=new Properties();
    
        //Deberia registrar un equipo complejo
    @Test
    public void CE1() throws IOException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        
        Modelo mod = new Modelo(100000,"Destornillador de estrella",null,"Destornillador",5000);
        EquipoComplejo ec=new EquipoComplejo(mod,"shdasdh564","ssaa");
        ec.setPlaca(123456);
        reg.save(ec);
        daof.commitTransaction();
        EquipoComplejo test=reg.load(ec.getPlaca());
        daof.endSession();  
        Assert.assertEquals(ec.toString(),test.toString());
    }
    
    //No deberia registrar dos veces el mismo equipo
    @Test
    public void CE2(){
        Modelo mod = new Modelo(100000,"Destornillador de estrella",null,"Destornillador",5000);
        EquipoComplejo ec=new EquipoComplejo(mod,"shdasdh564","ssaa");
        ec.setPlaca(123456);
        DAOFactory daof = null;
        try{
        properties.load(input);
        daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
       
        reg.save(ec);
        daof.commitTransaction();
        reg.save(ec);
        daof.commitTransaction();
        daof.endSession();
        Assert.fail("Registro dos veces el mismo equipo");
        }catch(IOException | PersistenceException e){
            System.out.println("Paso");
            Assert.assertEquals(e.getMessage(),"El equipo con nombre "+ec.getModelo_Eq().getNombre()+" ya esta registrado");
        }finally{
            daof.endSession();
        }
    }
    //Deberia registrar un equipo sencillo
    @Test
    public void CE3(){
        try {
            properties.load(input);
            DAOFactory daof=DAOFactory.getInstance(properties);
            System.out.println("Aqui va");
            daof.beginSession();
            System.out.println("Aqui va 2");
            DAOEquipoSencillo reg=daof.getDaoEquipoSencillo();
            System.out.println("Aqui va 3");
            EquipoSencillo es=new EquipoSencillo("Cable UTP","Cable",100,400);
            System.out.println("Aqui va 4");
            reg.save(es);
            System.out.println("Aqui va 5");
            daof.commitTransaction();
            System.out.println("Aqui va 6");
            EquipoSencillo test =reg.load(es.getNombre());
            System.out.println("Aqui va 7");
            daof.commitTransaction();
            System.out.println("Aqui va 8");
            System.out.println(test.toString());
            daof.endSession();
            System.out.println("Aqui va 9");
            System.out.println(reg);
            System.out.println(es);
            
            Assert.assertEquals("fallo",es.toString(),test.toString());
            //System.out.println(reg.loadByNombreDisponibles(es.getNombre()));
            System.out.println("Se murio");
        } catch (PersistenceException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //No deberia registrar mas de una vez el mismo equipo sencillo dos veces
    @Test
     public void CE4(){
         EquipoSencillo es=new EquipoSencillo("Cable UTP","Cable",100,400);
         DAOFactory daof=null;
        try{
            properties.load(input);
            daof=DAOFactory.getInstance(properties);
            daof.beginSession();
            DAOEquipoSencillo reg=daof.getDaoEquipoSencillo();

            reg.save(es);
            daof.commitTransaction();
            reg.save(es);
            daof.commitTransaction();
            daof.endSession();
            Assert.fail("Registro dos veces el mismo equipo"); 
        }catch(IOException | PersistenceException e){
            System.out.println("PASOASOSOASOASO");
            Assert.assertEquals(e.getMessage(),"El equipo con nombre "+es.getNombre()+" ya esta registrado");
    }finally{
            daof.endSession();
        }
  }
}
    
