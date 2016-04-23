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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


/**
 *
 * @author amoto
 */
public class RegistrarEquipoTest {
    public static InputStream input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
    public static Properties properties=new Properties();
    
     public RegistrarEquipoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Equipos_Sencillos");
        conn.commit();
    }
      
    
        //Deberia registrar un equipo complejo
    @Test
    public void CE1() throws IOException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        
        Modelo mod = new Modelo(100000,"Destornillador de estrella","udfyzsiudfyziduvz","Destornillador",5000);
        EquipoComplejo ec=new EquipoComplejo(mod,"shdasdh564","ssaa");
        ec.setPlaca(123456);
        ec.setEstado("hola");
        reg.save(ec);
        daof.commitTransaction();
        EquipoComplejo test=reg.load(ec.getPlaca());
        daof.endSession();
        Assert.assertEquals(ec,test);
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
            daof.beginSession();
            DAOEquipoSencillo reg=daof.getDaoEquipoSencillo();
            EquipoSencillo es=new EquipoSencillo("Cable UTP","Cable",100,400);
            reg.save(es);
            daof.commitTransaction();
            EquipoSencillo test =reg.load(es.getNombre());
            daof.commitTransaction();
            daof.endSession();
            Assert.assertEquals("fallo",es.toString(),test.toString());
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
            Assert.assertEquals(e.getMessage(),"El equipo con nombre "+es.getNombre()+" ya esta registrado");
    }finally{
            daof.endSession();
        }
  }
}
    
