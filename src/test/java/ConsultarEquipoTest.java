
import eci.pdsw.persistence.DAOEquipoComplejo;
import eci.pdsw.persistence.DAOFactory;
import eci.pdsw.persistence.DAOEquipoSencillo;
import eci.pdsw.entities.Modelo;
import eci.pdsw.entities.EquipoComplejo;
import eci.pdsw.entities.EquipoSencillo;
import eci.pdsw.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
 * @author Zawsx
 */
public class ConsultarEquipoTest {

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
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X");
        aConsultar.setPlaca(2);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo("Modelo de Prueba");
        daof.endSession();
        Assert.assertTrue(loaded.size() == 1 && aConsultar.equals(loaded.get(0)));
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
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X");
        EquipoComplejo aConsultar2 = new EquipoComplejo(model, "Asus", "BD5F");
        aConsultar2.setPlaca(3);
        aConsultar.setPlaca(2);
        aConsultar.setEstado("En prueba");
        aConsultar2.setEstado("En prueba");
        dec.save(aConsultar);
        dec.save(aConsultar2);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo("Modelo de Prueba");
        daof.endSession();
        Assert.assertTrue(loaded.size() == 2 && aConsultar.equals(loaded.get(0)) && aConsultar2.equals(loaded.get(1)));
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
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X");
        aConsultar.setPlaca(189);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        EquipoComplejo loaded = dec.load(189);
        Assert.assertEquals(aConsultar, loaded);
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
        EquipoComplejo aConsultar = new EquipoComplejo(model, "Toshiba", "AC3X");
        aConsultar.setPlaca(2);
        aConsultar.setEstado("En prueba");
        dec.save(aConsultar);
        daof.commitTransaction();
        EquipoComplejo loaded = dec.load("AC3X");
        System.out.println(loaded.toString());
        daof.endSession();
        Assert.assertEquals(aConsultar, loaded);
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
        //System.out.println(loaded.toString());
        Assert.assertEquals(aConsultar.toString(), loaded.toString());
    }
}
