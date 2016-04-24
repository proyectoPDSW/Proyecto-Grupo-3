
import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoException;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.entities.Modelo;
import eci.pdsw.entities.Persona;
import eci.pdsw.entities.Prestamo;
import eci.pdsw.entities.PrestamoIndefinido;
import eci.pdsw.entities.PrestamoTerminoFijo;
import eci.pdsw.persistence.DAOFactory;
import eci.pdsw.persistence.DAOPrestamo;
import eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
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
 * @author Hugo
 */
public class RegistrarPrestamosTest {
    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Equipos_Sencillos");
        stmt.execute("delete from Personas");
        stmt.execute("delete from Prestamos");
        conn.commit();
    }

    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido con 1 Equipo Complejo.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE01() throws IOException, EquipoException, PersistenceException {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido con 1 Equipo Sencillo.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE02() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,null,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(Timestamp.valueOf(LocalDateTime.now()));
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos complejos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE03() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos sencillos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE04() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,null,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por muchos equipos sencillos y muchos equipos complejos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE05() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo indefinido por 1 equipo sencillo y 1 equipo complejo.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE06() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo indefinido sin equipos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
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
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Prestamo p = new PrestamoIndefinido(per, null, null);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail();
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistrarPrestamosTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.assertEquals("No registro el prestamo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo indefinido sin persona.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
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
            Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
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
            Assert.fail();
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistrarPrestamosTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.assertEquals("No registro el prestamo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Complejo .
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE09() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadMorosos();
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Sencillo .
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE10() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, null, les, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Complejos .
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE11() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Sencillos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE12() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
        Prestamo p = new PrestamoTerminoFijo(per, null, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(time);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo muchos Equipos Complejos y muchos Equipos Sencillos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE13() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(time);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia registrar un prestamo Termino Fijo 1 Equipo Complejo y 1 Equipo Sencillo.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE14() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(time);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo Termino Fijo sin fecha.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
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
            Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
            EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            //Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
            Prestamo p = new PrestamoTerminoFijo(per, lec, les, null);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("Siguio y no debia");
            
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistrarPrestamosTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.assertEquals("No registro el prestamo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo Termino Fijo sin equipos.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
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
            Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
            Prestamo p = new PrestamoTerminoFijo(per, null, null, time);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("Siguio y no debia");
            
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistrarPrestamosTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.assertEquals("No registro el prestamo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
    /**
     * Clase de Equivalencia: no deberia registrar un prestamo Termino Fijo sin persona.
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     */
    @Test
    public void CE17() throws IOException, EquipoException{
        InputStream input;
            input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
            Properties properties = new Properties();
            properties.load(input);
            DAOFactory daof = DAOFactory.getInstance(properties);
        try {
            
            daof.beginSession();
            DAOPrestamo prestamo= daof.getDaoPrestamo();
            Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
            EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            //Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
            Prestamo p = new PrestamoTerminoFijo(null, lec, les, time);
            prestamo.save(p);
            daof.commitTransaction();
            Assert.fail("Siguio y no debia");
            
        } catch (PersistenceException ex) {
            Logger.getLogger(RegistrarPrestamosTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.assertEquals("No registro el prestamo", ex.getMessage());
        }finally{
            daof.endSession();
        }
    }
}
