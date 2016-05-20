import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.entities.Persona;
import edu.eci.pdsw.entities.Prestamo;
import edu.eci.pdsw.entities.PrestamoIndefinido;
import edu.eci.pdsw.entities.PrestamoTerminoFijo;
import edu.eci.pdsw.entities.Rol;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 1","Toshiba", null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","1000",dg,0);
        ec.setEstado(EquipoComplejo.indefinido);
        eqco.save(ec);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        //System.out.println(per.getCarnet());
        //System.out.println(eqco.load(ec.getModelo_Eq().getNombre(), ec.getSerial()).getEstado());
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
        
        Modelo model = new Modelo(6, "Modelo de prueba 2","Marca p", null, "Clase x", 100000);
        eqco.saveModelo(model);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
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
        Prestamo check=null;
        for (Prestamo prestamo1 : lp) {
            //System.out.println("Entro aqui");
            //System.out.println(prestamo1.toString());
            check = prestamo1;
        }
        Assert.assertTrue(p.equals(check));
        
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 3","Toshiba",null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        OrdenCompra dg2=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","26",dg,0);
        EquipoComplejo ec2 = new EquipoComplejo(model,"AC3Y","45",dg2,0);
        ec.setEstado(EquipoComplejo.indefinido);ec2.setEstado(EquipoComplejo.indefinido);
        eqco.save(ec);eqco.save(ec2);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,lec,null);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 4","Marca t", null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es);eqse.save(es1);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);lec.add(ec2);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);les.add(es1);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per,null,les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 4","Toshiba", null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        OrdenCompra dg2=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","26",dg,0);
        EquipoComplejo ec2 = new EquipoComplejo(model,"AC3Y","45",dg2,0);
        ec.setEstado(EquipoComplejo.indefinido);ec2.setEstado(EquipoComplejo.indefinido);
        eqco.save(ec);eqco.save(ec2);
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es);eqse.save(es1);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);lec.add(ec2);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);les.add(es1);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 5","Toshiba", null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","26",dg,0);
        ec.setEstado(EquipoComplejo.indefinido);
        eqco.save(ec);
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);//lec.add(ec2);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("profesor", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoIndefinido(per, lec, les);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
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
            
            Modelo model = new Modelo(4, "Modelo de prueba 6", "Marca d",null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());//les.put(es1,es1.getCantidadTotal());
            Set<EquipoSencillo> les = new HashSet<>(); 
            List<Rol> roles = new ArrayList<>();
            Rol r = new Rol("profesor", "1234", "sad");
            roles.add(r);
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
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
            Modelo model = new Modelo(4, "Modelo de prueba 7", "Marca t",null, "Clase x", 100000);
            OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
            OrdenCompra dg2=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
            EquipoComplejo ec = new EquipoComplejo(model,"AC3X","26",dg,0);
            EquipoComplejo ec2 = new EquipoComplejo(model,"AC3Y","45",dg2,0);
            ec.setEstado(EquipoComplejo.indefinido);ec2.setEstado(EquipoComplejo.indefinido);
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);//lec.add(ec2);
            //les.put(es1,es1.getCantidadTotal());
            //Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Set<EquipoSencillo> les = new HashSet<>(); 
            les.add(es);les.add(es1);
            List<Rol> roles = new ArrayList<>();
            Rol r = new Rol("profesor", "1234", "sad");
            roles.add(r);
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
        Modelo model = new Modelo(4, "Modelo de prueba 8","Marca v", null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","12",dg,0);
        ec.setEstado(EquipoComplejo.diario);
        eqco.save(ec);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);
        Set<EquipoSencillo> les = new HashSet<>(); 
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf("2000-2-1 0:0:0"),EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadMorosos();
        
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 9","A", null, "Clase x", 100000);
        eqco.saveModelo(model);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, null, les, Timestamp.valueOf("2000-2-1 0:0:0"),EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByCarne("2105533");
        
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 10", "x",null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        OrdenCompra dg2=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","12",dg,0);
        EquipoComplejo ec2 = new EquipoComplejo(model,"AC3Y","13",dg2,0);
        ec.setEstado(EquipoComplejo.diario);ec2.setEstado(EquipoComplejo.diario);
        eqco.save(ec);eqco.save(ec2);
        //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);lec.add(ec2);
        //Map<EquipoSencillo, Integer> les = new HashMap<>(); les.put(es,es.getCantidadTotal());
        Set<EquipoSencillo> les = new HashSet<>();
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Prestamo p = new PrestamoTerminoFijo(per, lec, null, Timestamp.valueOf("2000-2-1 0:0:0"),EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByEquipoComplejo(ec);
        
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 11","E" ,null, "Clase x", 100000);
        //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es1);eqse.save(es);
        //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);les.add(es1);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, null, les, time,EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
        
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 12", "S",null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        OrdenCompra dg2=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","12",dg,0);
        EquipoComplejo ec2 = new EquipoComplejo(model,"AC3Y","13",dg2,0);
        ec.setEstado(EquipoComplejo.diario);ec2.setEstado(EquipoComplejo.diario);
        eqco.save(ec);eqco.save(ec2);
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        eqse.save(es1);eqse.save(es);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);lec.add(ec2);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);les.add(es1);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time,EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
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
        
        Modelo model = new Modelo(4, "Modelo de prueba 13","G",null, "Clase x", 100000);
        eqco.saveModelo(model);
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
        EquipoComplejo ec = new EquipoComplejo(model,"AC3X","12",dg,0);
        ec.setEstado(EquipoComplejo.diario);
        eqco.save(ec);
        //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
        EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
        eqse.save(es);
        //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
        Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);//lec.add(ec2);
        Set<EquipoSencillo> les = new HashSet<>(); 
        les.add(es);
        List<Rol> roles = new ArrayList<>();
        Rol r = new Rol("estudiante", "1234", "sad");
        roles.add(r);
        Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
        persona.save(per);
        Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
        Prestamo p = new PrestamoTerminoFijo(per, lec, les, time,EquipoComplejo.diario);
        prestamo.save(p);
        daof.commitTransaction();
        List<Prestamo> lp = prestamo.loadByFecha(p.getFechaInicio());
        
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
            Modelo model = new Modelo(4, "Modelo de prueba 15","P",null, "Clase x", 100000);
            //EquipoComplejo ec = new EquipoComplejo(model, "Toshiba", "AC3X");
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            //EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            //List<EquipoComplejo> lec = new LinkedList<>(); lec.add(ec);//lec.add(ec2);
            Set<EquipoSencillo> les = new HashSet<>(); 
            List<Rol> roles = new ArrayList<>();
            Rol r = new Rol("estudiante", "1234", "sad");
            roles.add(r);
            Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494",roles);
            Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
            Prestamo p = new PrestamoTerminoFijo(per, null, null, time,EquipoComplejo.diario);
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
            Modelo model = new Modelo(4, "Modelo de prueba 16","L",null, "Clase x", 100000);
            OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi");
            EquipoComplejo ec = new EquipoComplejo(model,"AC3X","12",dg,0);
            ec.setEstado(EquipoComplejo.diario);
            //EquipoComplejo ec2 = new EquipoComplejo(model, "Toshib", "AC3Y");
            EquipoSencillo es = new EquipoSencillo("cable", "cable", 2000, 10);
            //EquipoSencillo es1 = new EquipoSencillo("cable UTP", "cable", 2000, 10);
            Set<EquipoComplejo> lec = new HashSet<>(); lec.add(ec);//lec.add(ec2);
            Set<EquipoSencillo> les = new HashSet<>(); 
            les.add(es);
            List<Rol> roles = new ArrayList<>();
            Rol r = new Rol("estudiante", "1234", "sad");
            roles.add(r);
            //Persona per = new Persona("2105533", "Hugo", "Alvarez", "hugo.alvarez@mqil.escuelaing.edu.co", "3014798494");
            Timestamp time = Timestamp.valueOf("2000-2-1 0:0:0");
            Prestamo p = new PrestamoTerminoFijo(null, lec, les, time,EquipoComplejo.diario);
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
