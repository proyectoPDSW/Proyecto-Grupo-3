/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author 2105684
 */
public class ConsultaPrestamosTest {
    
    public ConsultaPrestamosTest() {
    }
    
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
     * Clase de Equivalencia: deberia consultar un prestamo indefinido por carne.
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
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,lec,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo indefinido por fecha.
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
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,lec,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(Timestamp.valueOf(LocalDateTime.now()));
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo indefinido por equipo complejo.
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
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoIndefinido(per,lec,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo Termino Fijo por mora .
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
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadMorosos();
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo Termino Fijo por carne .
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
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo Termino Fijo por Equipo Complejo .
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
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0)));
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
    /**
     * Clase de Equivalencia: deberia consultar un prestamo Termino Fijo por fecha .
     * @throws java.io.IOException
     * @throws eci.pdsw.entities.EquipoException
     * @throws eci.pdsw.persistence.PersistenceException
     */
    @Test
    public void CE07() throws IOException, EquipoException, PersistenceException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOPrestamo prestamo= daof.getDaoPrestamo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
        Timestamp time = Timestamp.valueOf(LocalDateTime.of(2000, Month.MARCH, 1, 0, 0));
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(time);
        Assert.assertEquals(1,lp.size());
        daof.endSession();
    }
}
