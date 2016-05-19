/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoTerminoFijo;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Julian Devia
 */
public class DevolucionesTest {
    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Rol_Persona");
        stmt.execute("delete from Rol");
        stmt.execute("delete from Departamento_persona");
        stmt.execute("delete from Departamentos");
        stmt.execute("delete from Equipo_prestamo_complejo");
        stmt.execute("delete from Equipo_prestamo_sencillo");
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Modelos");
        stmt.execute("delete from Equipos_Sencillos");
        stmt.execute("delete from Prestamos");
        stmt.execute("delete from Personas");
        conn.commit();
    }

    @Test
    /**
     * El prestamo queda terminado cuando no le quedan equipos por devolver
     * 
     */
    public void CE01() throws IOException, EquipoException {
        //InputStream input;
        //input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        //Properties properties = new Properties();
        //properties.load(input);
        //DAOFactory daof = DAOFactory.getInstance(properties);
        //daof.beginSession();
        //DAOPrestamo dapr=daof.getDaoPrestamo();
        //DAOEquipoComplejo daec=daof.getDaoEquipoComplejo();
        //DAOEquipoSencillo daes=daof.getDaoEquipoSencillo();
        //DAOPersona dape=daof.getDaoPersona();
        
        Persona yo= new Persona("2105684", "Julian", "Devia", "julian.devia@mail.escuelaing.edu.co", "6030405", null);
        Modelo mod=new Modelo(12, "hola", null, "asd", 123);
        OrdenCompra dg=null;
        EquipoComplejo ec=new EquipoComplejo(true, true, EquipoComplejo.almacen, "qwe2", "123", "asd", mod,dg,0);
        EquipoSencillo es=new EquipoSencillo("nombre", "clase", 3, 0);
        HashSet<EquipoComplejo> sec=new HashSet<>();sec.add(ec);
        HashSet<EquipoSencillo> ses=new HashSet<>();ses.add(es);
        Prestamo pr=new PrestamoTerminoFijo(yo, sec, ses, Timestamp.valueOf("2016-03-05 12:00:00"), EquipoComplejo.diario);
        pr.getEquiposComplejosFaltantes();pr.getEquiposSencillosFaltantes();
        //daof.endSession();
        Assert.assertTrue("El prestamo no queda terminado si no tiene equipos pendientes",pr.terminado());
    }
    @Test
    /**
     * Se actualiza la fecha de fin cuando el prestamo está terminado
     * 
     */
    public void CE02() throws IOException {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        try{
            
            DAOPrestamo dapr=daof.getDaoPrestamo();
            DAOEquipoComplejo daec=daof.getDaoEquipoComplejo();
            DAOEquipoSencillo daes=daof.getDaoEquipoSencillo();
            DAOPersona dape=daof.getDaoPersona();

            Persona yo= new Persona("2105684", "Julian", "Devia", "julian.devia@mail.escuelaing.edu.co", "6030405", null);
            Modelo mod=new Modelo(12, "hola", null, "asd", 123);
            OrdenCompra dg=null;
            EquipoComplejo ec=new EquipoComplejo(true, true, EquipoComplejo.almacen, "qwe2", "123", "asd", mod,dg,0);
            EquipoSencillo es=new EquipoSencillo("xnombre", "clase", 3, 0);
            dape.save(yo);
            daof.commitTransaction();
            daec.save(ec);
            daof.commitTransaction();
            daes.save(es);
            daof.commitTransaction();
            HashSet<EquipoComplejo> sec=new HashSet<>();sec.add(ec);
            HashSet<EquipoSencillo> ses=new HashSet<>();ses.add(es);
            Prestamo pr=new PrestamoTerminoFijo(yo, sec, ses, Timestamp.valueOf("2016-03-05 12:00:00"), EquipoComplejo.diario);
            dapr.save(pr);
            daof.commitTransaction();
            dapr.update(pr);
            daof.commitTransaction();
            Timestamp curr=Prestamo.currDate();
            Prestamo loaded=dapr.load(pr.getFechaInicio(), pr.getElQuePideElPrestamo().getCarnet());
            Assert.assertTrue("La fecha de fin real no es la correcta",(new Timestamp(curr.getTime()+10)).after(loaded.getFechaRealEntregada()));
        }catch(EquipoException| PersistenceException e){
            Assert.fail("lanza excepcion actualizando el prestamo");
        }finally{
            daof.endSession();
        }
        
    }
    @Test
    /**
     * Se actualiza el equipo sencillo del prestamo
     * 
     */
    public void CE03() throws IOException {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        try{
            
            DAOPrestamo dapr=daof.getDaoPrestamo();
            DAOEquipoComplejo daec=daof.getDaoEquipoComplejo();
            DAOEquipoSencillo daes=daof.getDaoEquipoSencillo();
            DAOPersona dape=daof.getDaoPersona();

            Persona yo= new Persona("2105684", "Julian", "Devia", "julian.devia@mail.escuelaing.edu.co", "6030405", null);
            Modelo mod=new Modelo(12, "hola", null, "asd", 123);
            OrdenCompra dg=null;
            EquipoComplejo ec=new EquipoComplejo(true, true, EquipoComplejo.almacen, "qwe2", "123", "asd", mod,dg,0);
            EquipoSencillo es=new EquipoSencillo("xnombre", "clase", 3, 4);
            dape.save(yo);
            daof.commitTransaction();
            daec.save(ec);
            daof.commitTransaction();
            daes.save(es);
            daof.commitTransaction();
            HashSet<EquipoComplejo> sec=new HashSet<>();sec.add(ec);
            HashSet<EquipoSencillo> ses=new HashSet<>();ses.add(es);
            Prestamo pr=new PrestamoTerminoFijo(yo, sec, ses, Timestamp.valueOf("2016-03-05 12:00:00"), EquipoComplejo.diario);
            dapr.save(pr);
            daof.commitTransaction();
            es.setCantidadTotal(0);
            //Prestamo loaded=dapr.load(pr.getFechaInicio(), pr.getElQuePideElPrestamo().getCarnet());
            ses=new HashSet<>();ses.add(es);
            pr.setEquiposSencillosPrestados(ses);
            dapr.update(pr);
            daof.commitTransaction();
            Prestamo loaded=dapr.load(pr.getFechaInicio(), pr.getElQuePideElPrestamo().getCarnet());
            //System.out.println("pr: "+pr);
            //System.out.println(loaded);
            daof.endSession();
            
            EquipoSencillo b=null;
            for(EquipoSencillo e:loaded.getEquiposSencillosPrestados()){
                //System.out.println("test "+e);
                if(e.getNombre().equals(es.getNombre())) b=e;
            }
            Assert.assertEquals("La cantidad del equipo sencillo no es correcta",b.getCantidadTotal(),0);
        }catch(EquipoException| PersistenceException e){
            Assert.fail("lanza excepcion actualizando el equipo sencillo del prestamo");
        }finally{
            daof.endSession();
        }
        
    }
}
