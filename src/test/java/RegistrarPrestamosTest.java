

import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.After;
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
        conn.commit();
    }
    
    //Deberia registrar un prestamo con un equipo complejo
    public void CE1() throws IOException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
        
    }
    
    //Deberia registrar un prestamo con un equipo sencillo
    public void CE2() throws IOException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
    }
    
    //Deberia registrar dos prestamos para una misma persona
    public void CE3() throws IOException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        
        DAOEquipoComplejo dec=daof.getDaoEquipoComplejo();
        DAOEquipoSencillo des=daof.getDaoEquipoSencillo();
        DAOPersona dp = daof.getDaoPersona();
        DAOPrestamo dpres=daof.getDaoPrestamo();
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
    }
   
}
