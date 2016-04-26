
import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoIndefinido;
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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hugo Alvarez
 */
public class PrestamosTest {
    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Equipo_prestamo_complejo");
        stmt.execute("delete from Equipo_prestamo_sencillo");
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Modelos");
        stmt.execute("delete from Equipos_Sencillos");
        stmt.execute("delete from Prestamos");
        stmt.execute("delete from Personas");
        conn.commit();
    }

    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido con 1 Equipo Complejo.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE01() throws IOException, EquipoException, PersistenceException {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 1", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        eqco.save(ec);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne(per.getCarnet());
        daof.endSession();
        Prestamo check=null;
        for (Prestamo prestamo1 : lp) {
            //System.out.println("Entro aqui");
            //System.out.println(prestamo1.toString());
            check = prestamo1;
        }
        Assert.assertTrue(p.equals(check));
        
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido con 1 Equipo Sencillo.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE02() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(6, "Modelo de prueba 2", null, "Clase x", 100000);
        eqco.save(model);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,null,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(new Timestamp(new Date().getTime()));
        /*for (Prestamo prestamo1 : lp) {
            System.out.println("Entro aqui");
            System.out.println(prestamo1.toString());
        }*/
        daof.endSession();
        Assert.assertEquals(1,lp.size());
        
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos complejos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE03() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 3", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        ec.setPlaca(26);
        ec2.setPlaca(45);
        eqco.save(ec);eqco.save(ec2);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        daof.endSession();
        Assert.assertEquals(1,lp.size());
        
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos sencillos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE04() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 4", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es);eqse.save(es1);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,null,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos sencillos y muchos equipos complejos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE05() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 4", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        ec.setPlaca(26);
        ec2.setPlaca(45);
        eqco.save(ec);eqco.save(ec2);
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es);eqse.save(es1);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        for (Prestamo prestamo1 : lp) {
            
        }
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por 1 equipo sencillo y 1 equipo complejo.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE06() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 5", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        ec.setPlaca(26);eqco.save(ec);
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo indefinido sin equipos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     */
    @Test
    public void CE07() throws IOException, EquipoException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        try {
            
            daof.beginSession();
            
            DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
            DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
            DAOPersona persona = daof.getDaoPersona();
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            
            Modelo model = new Modelo(4, "Modelo de prueba 6", null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","profesor");
            persona.save(per);
            Prestamo p = new PrestamoIndefinido(per, null, null);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("No debio continuar");
        } catch (PersistenceException ex) {
            Assert.assertEquals("Los equipos no pueden ser nulos", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo indefinido sin persona.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     */
    @Test
    public void CE08() throws IOException, EquipoException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        try {
            
            daof.beginSession();
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            Modelo model = new Modelo(4, "Modelo de prueba 7", null, "Clase x", 100000);
            EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            //Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Prestamo p = new PrestamoIndefinido(null, lec, les);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("No debio continuar");
        } catch (PersistenceException ex) {
            Assert.assertEquals("La persona no puede ser nulo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Complejo .
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE09() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba 8", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        ec.setPlaca(12);
        eqco.save(ec);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf("2000-2-1 0:0:0"));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadMorosos();
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Sencillo .
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE10() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 9", null, "Clase x", 100000);
        eqco.save(model);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, null, les, Timestamp.valueOf("2000-2-1 0:0:0"));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Complejos .
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE11() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 10", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        ec.setPlaca(12);ec2.setPlaca(13);
        eqco.save(ec);eqco.save(ec2);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf("2000-2-1 0:0:0"));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Sencillos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE12() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 11", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es1);eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, null, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Complejos y muchos Equipos Sencillos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE13() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 12", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        ec.setPlaca(12);ec2.setPlaca(13);
        eqco.save(ec);eqco.save(ec2);
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es1);eqse.save(es);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Complejo y 1 Equipo Sencillo.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     * @throws edu.eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE14() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo eqco= daof.getDaoEquipoComplejo();
        DAOEquipoSencillo eqse=daof.getDaoEquipoSencillo();
        DAOPersona persona = daof.getDaoPersona();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        
        Modelo model = new Modelo(4, "Modelo de prueba 13", null, "Clase x", 100000);
        eqco.save(model);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        eqco.save(ec);
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","estudiante");
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
        
        daof.endSession();
        Assert.assertEquals(1,lp.size());
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo Termino Fijo sin equipos.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     */
    @Test
    public void CE15() throws IOException, EquipoException{
        InputStream input;
            input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
            Properties properties = new Properties();
            properties.load(input);
            DAOFactory daof = DAOFactory.getInstance(properties);
        try {
            
            daof.beginSession();
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            Modelo model = new Modelo(4, "Modelo de prueba 15", null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494","Estudiante");
            Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
            Prestamo p = new PrestamoTerminoFijo(per, null, null, time);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("Siguio y no debia");
            
        } catch (PersistenceException ex) {
            Assert.assertEquals("Los equipos no pueden ser nulos", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo Termino Fijo sin persona.
     * @throws java.io.IOException
     * @throws edu.eci.pdsw.entities.EquipoException
     */
    @Test
    public void CE16() throws IOException, EquipoException{
        InputStream input;
            input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
            Properties properties = new Properties();
            properties.load(input);
            DAOFactory daof = DAOFactory.getInstance(properties);
        try {
            
            daof.beginSession();
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            Modelo model = new Modelo(4, "Modelo de prueba 16", null, "Clase x", 100000);
            EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            //Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
            Prestamo p = new PrestamoTerminoFijo(null, lec, les, time);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("Siguio y no debia");
            
        } catch (PersistenceException ex) {
            Assert.assertEquals("La persona no puede ser nulo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
}
