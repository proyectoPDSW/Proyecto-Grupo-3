

import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoException;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.OrdenCompra;
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author german
 */
public class FichaEquipoTest {
    public static InputStream input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
    public static Properties properties=new Properties();
    
     public FichaEquipoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("delete from Informacion_Compra");
        stmt.execute("delete from Equipos_Complejos");
        stmt.execute("delete from Equipos_Sencillos");
        stmt.execute("delete from Modelos");
        conn.commit();
    }
    
    //Deberia registrar el equipo complejo con su orden de compra
    @Test
    public void CE1() throws IOException, EquipoException, PersistenceException{
        properties.load(input);
        DAOFactory daof=DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo reg=daof.getDaoEquipoComplejo();
        
        Modelo mod=new Modelo(10000,"Nombre1","Marca1","Foto1","Clase1",100000);
        reg.saveModelo(mod);
        daof.commitTransaction();
        OrdenCompra dg=new OrdenCompra(Timestamp.valueOf("2000-2-2 0:0:0"),Timestamp.valueOf("2001-2-2 0:0:0"),"Holi","orden","activo");
        EquipoComplejo ec=new EquipoComplejo(mod,"Serial1","Placa1",dg,0);
        reg.save(ec);
        daof.commitTransaction();
        reg.saveOrdenCompra(dg,ec.getSerial(), ec.getModelo_Eq());
        daof.commitTransaction();
        OrdenCompra ogTest= reg.loadOrdenCompraBySerial(ec.getSerial());
        daof.endSession();
        Assert.assertEquals("No registro la orden de compra",ogTest,ec.getOrdenCompra());
    }
}