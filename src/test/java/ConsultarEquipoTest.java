
import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.entities.Modelo;
import edu.eci.pdsw.entities.EquipoComplejo;
import edu.eci.pdsw.entities.EquipoSencillo;
import edu.eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
 * @author David Useche
 */
public class ConsultarEquipoTest {
    
     public ConsultarEquipoTest() {
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
        stmt.execute("delete from Modelos");
        conn.commit();
    }

    //Clase equivalencia 1, Deberia consultar un equipo por modelo
    @Test
    public void deberiaConsultarUnoPorModelo() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo dec = daof.getDaoEquipoComplejo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X",734829);
        aConsultar.setPlaca(2);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo(aConsultar.getModelo_Eq().getNombre());
        daof.commitTransaction();
        daof.endSession();
        Assert.assertTrue("no consulta adecuadamente uno por equipo",loaded.size() == 1 && aConsultar.equals(loaded.get(0)));
    }

    //Clase equivalencia 2, Deberia consultar varios equipos por modelo
    @Test
    public void deberiaConsultarVariosPorModelo() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo dec = daof.getDaoEquipoComplejo();
        Modelo model = new Modelo(5, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X",38294);
        EquipoComplejo aConsultar2 = new EquipoComplejo(model, "Asus", "BD5F",74892);
        aConsultar.setEstado("En prueba");
        aConsultar2.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        dec.save(aConsultar2);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo(aConsultar.getModelo_Eq().getNombre());
        daof.commitTransaction();
        daof.endSession();
        Assert.assertTrue("no consulta adecuadamente varios por equipo",loaded.size() == 2 && aConsultar.equals(loaded.get(0)) && aConsultar2.equals(loaded.get(1)));
    }

    //Clase equivalencia 3, Deberia consultar equipo por placa
    @Test
    public void deberiaConsultarPorPlaca() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo dec = daof.getDaoEquipoComplejo();
        Modelo model = new Modelo(6, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X",189);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        EquipoComplejo loaded = dec.load(189);
        Assert.assertEquals("no conulta adecuadamente por placa",aConsultar, loaded);
    }

    //Clase equivalencia 4, Deberia consultar equipo por serial
    @Test
    public void deberiaConsultarPorSerial() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo dec = daof.getDaoEquipoComplejo();
        Modelo model = new Modelo(7, "Modelo de prueba", null, "Clase x", 100000);
        //dec.save(model);
        //daof.commitTransaction();
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X",2);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        EquipoComplejo loaded = dec.load(model.getNombre(),"AC3X");
        daof.endSession();
        Assert.assertEquals("no consulta adecuadamente por serial",aConsultar, loaded);
    }

    //Clase equivalencia 5, Deberia consultar Equipo sencillo por nombre
    @Test
    public void deberiaConsultarPorNombre() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoSencillo des = daof.getDaoEquipoSencillo();
        EquipoSencillo aConsultar = new EquipoSencillo("Cable", "Electronica", 1000, 100);
        des.save(aConsultar);
        daof.commitTransaction();
        EquipoSencillo loaded = des.load("Cable");
        daof.endSession();
        Assert.assertEquals("no consulta adecuadamente por nombre",aConsultar.toString(), loaded.toString());
    }
    
    //Clase equivalencia 6, Deberia consultar modelos por aproximacion
    @Test
    public void deberiaConsultarAproximados() throws Exception{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo des=daof.getDaoEquipoComplejo();
        Modelo model1=new Modelo(6, "Modelo de prueba", null, "Clase x", 100000);
        Modelo model2=new Modelo(6, "Modelo de prueba2", null, "Clase y", 100000);
        Modelo model3=new Modelo(6, "Nada que ver", null, "Clase z", 100000);
        des.save(model1);
        des.save(model2);
        des.save(model3);
        daof.commitTransaction();
        ArrayList<String>ans=new ArrayList<>();
        ans.add(model1.getNombre());
        ans.add(model2.getNombre());
        List<String>loaded=des.loadModelosAproximados("Modelo");
        //System.out.println(loaded.size());
        //System.out.println(loaded.get(0)+" "+loaded.get(1)+" "+model1+ " "+model2);
        Assert.assertTrue("No son iguales",loaded.size()==2 && (loaded.get(0).equals(ans.get(0)) || 
                loaded.get(0).equals(ans.get(1))) && (loaded.get(1).equals(ans.get(0)) || 
                loaded.get(1).equals(ans.get(1))));
    }
    
    //Clase equivalencia 7, Deberia consultar los equipos en almacen de un modelo
    @Test
    public void deberiaConsultarEnAlmacenPorModelo() throws Exception {
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        DAOFactory daof = DAOFactory.getInstance(properties);
        daof.beginSession();
        DAOEquipoComplejo dec = daof.getDaoEquipoComplejo();
        Modelo model = new Modelo(4, "Modelo de prueba", null, "Clase x", 100000);
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X",734829);
        EquipoComplejo aConsultar1 = new EquipoComplejo(model, "Toshiba", "ACasd",734829);
        EquipoComplejo aConsultar2 = new EquipoComplejo(model, "Toshiba", "AC23d",734829);
        aConsultar.setPlaca(2);
        aConsultar2.setPlaca(3);
        aConsultar1.setPlaca(4);
        aConsultar.setEstado(EquipoComplejo.almacen);
        aConsultar2.setEstado(EquipoComplejo.almacen);
        aConsultar1.setEstado(EquipoComplejo.diario);
        dec.save(aConsultar);
        dec.save(aConsultar1);
        dec.save(aConsultar2);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadEnAlmacenByModelo(aConsultar.getModelo_Eq().getNombre());
        daof.commitTransaction();
        daof.endSession();
        Assert.assertTrue("No carga los equipos en almacen", loaded.contains(aConsultar) && loaded.contains(aConsultar2) && loaded.size()==2);
    }

}
