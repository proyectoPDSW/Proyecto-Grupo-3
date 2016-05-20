/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.persistence.mybatis;

import edu.eci.pdsw.persistence.DAOEquipoComplejo;
import edu.eci.pdsw.persistence.DAOEquipoSencillo;
import edu.eci.pdsw.persistence.DAOFactory;
import edu.eci.pdsw.persistence.DAOPersona;
import edu.eci.pdsw.persistence.DAOPrestamo;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author German Lopez
 */
public class MyBatisDAOFactory extends DAOFactory {

    private static SqlSessionFactory sqlSessionFabrica;
    private SqlSession sesion;

    public static SqlSessionFactory getSqlSessionFactory(String config) {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream(config);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }

    public MyBatisDAOFactory(String properties) {
        sqlSessionFabrica = getSqlSessionFactory(properties);
    }

    @Override
    public void beginSession() {
        sesion = sqlSessionFabrica.openSession();
    }

    @Override
    public void commitTransaction() {
        sesion.commit();
    }

    @Override
    public void rollbackTransaction() {
        sesion.rollback();
    }

    @Override
    public void endSession() {
        sesion.close();
    }

    @Override
    public DAOEquipoComplejo getDaoEquipoComplejo() {
        return new MyBatisDAOEquipoComplejo(sesion);
    }

    @Override
    public DAOEquipoSencillo getDaoEquipoSencillo() {
        return new MyBatisDAOEquipoSencillo(sesion);
    }

    @Override
    public DAOPersona getDaoPersona() {
        return new MyBatisDAOPersona(sesion);
    }

    @Override
    public DAOPrestamo getDaoPrestamo() {
        return new MyBatisDAOPrestamo(sesion);
    }

}
