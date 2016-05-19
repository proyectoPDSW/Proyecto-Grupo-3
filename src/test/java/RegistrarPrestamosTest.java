

import edu.eci.pdsw.entities.DatosGenerales;
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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author German Lopez
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
    @Test
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
        DatosGenerales dg=new DatosGenerales("nombre","marca","serial",Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi",50000,3);
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial","123456",dg,0);
        ec.setEstado(EquipoComplejo.diario);
        dec.save(ec);
        daof.commitTransaction();
        Set<EquipoComplejo> equipos=new LinkedHashSet<>();
        equipos.add(ec);
        Set<EquipoSencillo> equiS=new LinkedHashSet<>();
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();

        Prestamo pres=new PrestamoTerminoFijo(p,equipos,equiS,Timestamp.valueOf("2016-7-10 0:0:0"),EquipoComplejo.diario);

        dpres.save(pres);
        daof.commitTransaction();
        List<Prestamo> test=dpres.loadByCarne(p.getCarnet());
        daof.commitTransaction();
        daof.endSession();
        Prestamo test1=test.get(0);
        Assert.assertEquals("No se registro bien el prestamo con equipo complejo",test1, pres);
    }
    
    //Deberia registrar un prestamo con un equipo sencillo
    @Test
    public void CE2() throws IOException, EquipoException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        
        Set<EquipoComplejo> equipos=new HashSet<>();
        EquipoSencillo es=new EquipoSencillo("nombre","clase",3,123456);
        
        des.save(es);
        daof.commitTransaction();
        Set<EquipoSencillo> equiS=new LinkedHashSet<>();
        equiS.add(es);
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoTerminoFijo(p,equipos,equiS,Timestamp.valueOf("2016-7-10 0:0:0"),EquipoComplejo.diario);
        dpres.save(pres);
        daof.commitTransaction();
        List<Prestamo> test=dpres.loadByCarne(p.getCarnet());
        daof.commitTransaction();
        daof.endSession();
        Prestamo test1=test.get(0);
        Assert.assertEquals("No se registro bien el prestamo con equipo sencillo",test1, pres);
    }
    
    //Deberia registrar dos prestamos para una misma persona
    @Test
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
        DatosGenerales dg=new DatosGenerales("nombre","marca","serial",Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi",50000,3);
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial","123456",dg,0);
        ec.setEstado(EquipoComplejo.diario);
        dec.save(ec);
        daof.commitTransaction();
        Set<EquipoComplejo> equipos=new HashSet<>();
        equipos.add(ec);
        Set<EquipoComplejo> ninguno=new HashSet<>();
        EquipoSencillo es=new EquipoSencillo("otro nombre","otra clase",3,123456);
        //ec.setEstado(EquipoComplejo.indefinido);
        des.save(es);
        daof.commitTransaction();
        Set<EquipoSencillo> equiS=new HashSet<>();
        equiS.add(es);
        Set<EquipoSencillo> nulo=new HashSet<>();
        Rol r=new Rol("Estudiante","yuiewq","1232143");
        ArrayList<Rol> roles=new ArrayList<>();
        roles.add(r);
        Persona p=new Persona("2105403","German","Lopez","german.lopez-p@mail.escuelaing.edu.co","8945357",roles);
        dp.save(p);
        daof.commitTransaction();
        Prestamo pres=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),ninguno,equiS,p,EquipoComplejo.diario);
        Prestamo pres1=new PrestamoTerminoFijo(Timestamp.valueOf("2000-2-1 0:0:1"),Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2000-2-2 0:0:0"),equipos,nulo,p,EquipoComplejo.diario);
        dpres.save(pres);
        daof.commitTransaction();
        dpres.save(pres1);
        daof.commitTransaction();
        List<Prestamo> test=dpres.loadByCarne( pres.getElQuePideElPrestamo().getCarnet());
        Prestamo test1=test.get(0);
        Prestamo test2=test.get(1);
        daof.commitTransaction();
        daof.endSession();
        Assert.assertTrue("No se registro bien el primer prestamo",pres.equals(test2) || pres.equals(test1));
        Assert.assertTrue("No se registro bien el segundo prestamo",pres1.equals(test2) || pres1.equals(test1));
    }
    
    //No deberia registrar dos veces el mismo prestamo
    @Test
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
        DatosGenerales dg=new DatosGenerales("nombre","marca","serial",Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi",50000,3);
        EquipoComplejo ec=new EquipoComplejo(mod,"marca","serial","54351",dg,0);
        ec.setEstado(EquipoComplejo.indefinido);
        dec.save(ec);
        daof.commitTransaction();
        Set<EquipoComplejo> equipos=new HashSet<>();
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
