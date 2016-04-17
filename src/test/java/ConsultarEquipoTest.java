
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import pdswgr3.pdsw.persistence.*;
import pdswgr3.pdsw.entities.*;

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
        Modelo model = new Modelo(1, 100, "ModeloPrueba", null, "Operario", 100000000);
        EquipoComplejo aConsultar = new EquipoComplejo(1, true, true, "Disponible", "1823", null, "Toshiba", model);
        dec.save(aConsultar);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo("ModeloPrueba");
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
        Modelo model = new Modelo(1, 100, "ModeloPrueba", null, "Operario", 100000000);
        EquipoComplejo aConsultar = new EquipoComplejo(1, true, true, "Disponible", "1823", null, "Toshiba", model);
        EquipoComplejo aConsultar2 = new EquipoComplejo(2, true, true, "Disponible", null, null, "Asus", model);
        dec.save(aConsultar);
        dec.save(aConsultar2);
        daof.commitTransaction();
        ArrayList<EquipoComplejo> loaded = dec.loadByModelo("ModeloPrueba");
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
        Modelo model = new Modelo(1, 100, "ModeloPrueba", null, "Operario", 100000000);
        EquipoComplejo aConsultar = new EquipoComplejo(1, true, true, "Disponible", "1823", 189, "Toshiba", model);
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
        Modelo model = new Modelo(1, 100, "ModeloPrueba", null, "Operario", 100000000);
        EquipoComplejo aConsultar = new EquipoComplejo(1, true, true, "Disponible", "1823", null, "Toshiba", model);
        dec.save(aConsultar);
        daof.commitTransaction();
        EquipoComplejo loaded = dec.load("1823");
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
        EquipoSencillo aConsultar=new EquipoSencillo("Cable",null,"Electronica",100,500);
        des.save(aConsultar);
        daof.commitTransaction();
        EquipoSencillo loaded=des.load("Cable");
        daof.endSession();
        Assert.assertEquals(aConsultar, loaded);
    }
}
