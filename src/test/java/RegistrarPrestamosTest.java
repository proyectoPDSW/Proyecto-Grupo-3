

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
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
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2105403
 */
public class RegistrarPrestamosTest {
    public static InputStream input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
    public static Properties properties=new Properties();
    
     public RegistrarPrestamosTest() {
    }

    @Before
    public void setUp() {
    }

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
        stmt.execute("delete from Rol");
        stmt.execute("delete from Rol_Persona");
        conn.commit();
    }
    
    //Deberia registrar un prestamo con un equipo complejo
    public void CE1() throws IOException, EquipoException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        
        Modelo mod=new Modelo(400,"nombre","foto","clase",50000);
        dec.save(mod);
        daof.commitTransaction();
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial",123456);
        dec.save(ec);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> equipos=new ArrayList<>();
        equipos.add(ec);
        ArrayList<EquipoSencillo> equiS=new ArrayList<>();
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),equipos,equiS,p,1);
        dpres.save(pres);
        daof.commitTransaction();
        List<Prestamo> test=dpres.load(pres.getFechaInicio(), pres.getElQuePideElPrestamo().getCarnet());
        Prestamo test1=test.get(0);
        daof.commitTransaction();
        daof.endSession();
        Assert.assertEquals("No se registro bien el prestamo con equipo complejo",test1.toString(), pres.toString());
    }
    
    //Deberia registrar un prestamo con un equipo sencillo
    public void CE2() throws IOException, EquipoException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        
        ArrayList<EquipoComplejo> equipos=new ArrayList<>();
        EquipoSencillo es=new EquipoSencillo("nombre","clase",3,123456);
        des.save(es);
        daof.commitTransaction();
        ArrayList<EquipoSencillo> equiS=new ArrayList<>();
        equiS.add(es);
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),equipos,equiS,p,1);
        dpres.save(pres);
        daof.commitTransaction();
        List<Prestamo> test=dpres.load(pres.getFechaInicio(), pres.getElQuePideElPrestamo().getCarnet());
        Prestamo test1=test.get(0);
        daof.commitTransaction();
        daof.endSession();
        Assert.assertEquals("No se registro bien el prestamo con equipo sencillo",test1.toString(), pres.toString());
    }
    
    //Deberia registrar dos prestamos para una misma persona
    public void CE3() throws IOException, EquipoException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        
        Modelo mod=new Modelo(400,"nombre","foto","clase",50000);
        dec.save(mod);
        daof.commitTransaction();
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial",123456);
        dec.save(ec);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> equipos=new ArrayList<>();
        equipos.add(ec);
        ArrayList<EquipoComplejo> ninguno=new ArrayList<>();
        EquipoSencillo es=new EquipoSencillo("nombre","clase",3,123456);
        des.save(es);
        daof.commitTransaction();
        ArrayList<EquipoSencillo> equiS=new ArrayList<>();
        equiS.add(es);
        ArrayList<EquipoSencillo> nulo=new ArrayList<>();
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),ninguno,equiS,p,1);
        Prestamo pres1=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),equipos,nulo,p,1);
        dpres.save(pres);
        daof.commitTransaction();
        dpres.save(pres1);
        daof.commitTransaction();
        List<Prestamo> test=dpres.load(pres.getFechaInicio(), pres.getElQuePideElPrestamo().getCarnet());
        Prestamo test1=test.get(0);
        Prestamo test2=test.get(1);
        daof.commitTransaction();
        daof.endSession();
        Assert.assertEquals("No se registro bien el primer prestamo",test1.toString(), pres.toString());
        Assert.assertEquals("No se registro bien el segundo prestamo",test2.toString(), pres1.toString());
    }
    
    //No deberia registrar dos veces el mismo prestamo
    public void CE4() throws IOException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        try{
        Modelo mod=new Modelo(400,"nombre","foto","clase",5000);
        dec.save(mod);
        daof.commitTransaction();
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial",54351);
        dec.save(ec);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> equipos=new ArrayList<>();
        equipos.add(ec);
        Rol r=new Rol("Profesor","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("12529585","Hector","Cadavid","hector.cadavid@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoIndefinido(p,equipos,null);
        dpres.save(pres);
        daof.commitTransaction();
        dpres.save(pres);
        daof.commitTransaction();
        daof.endSession();
        Assert.fail("Registro dos veces el mismo prestamo");
        }catch(PersistenceException | EquipoException ex){
            Assert.assertEquals("El prestamo ya existe", ex.getMessage());
        }
    }
   
}
