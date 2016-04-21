/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence.mybatis;

import eci.pdsw.persistence.DAOEquipoComplejo;
import eci.pdsw.persistence.DAOEquipoSencillo;
import eci.pdsw.persistence.DAOFactory;
import eci.pdsw.persistence.DAOPersona;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author 2107803
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
        return new MybatisDAOEquipoComplejo(sesion);
    }

    @Override
    public DAOEquipoSencillo getDaoEquipoSencillo() {
        return new MyBatisDAOEquipoSencillo(sesion);
    }

    @Override
    public DAOPersona getDaoPersona() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
