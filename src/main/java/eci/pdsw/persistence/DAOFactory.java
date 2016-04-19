/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.persistence;

import java.util.Properties;
import eci.pdsw.persistence.mybatis.MyBatisDAOFactory;
/**
 *
 * @author Zawsx
 */
public abstract class DAOFactory {

    private static DAOFactory instance = null;

    public static DAOFactory getInstance(Properties appProperties) {
        if (instance == null) {
            synchronized (DAOFactory.class) {
                if (instance == null) {
                    if (appProperties.get("dao").equals("mybatis")) {
                        instance = new MyBatisDAOFactory(appProperties.getProperty("config"));
                    } else {
                        throw new RuntimeException("Wrong configuration: Unsupported DAO:" + appProperties.get("dao"));
                    }
                }
            }
        }
        return instance;
    }

    public abstract void beginSession();

    public abstract void commitTransaction();

    public abstract void rollbackTransaction();

    public abstract void endSession();

    public abstract DAOEquipoComplejo getDaoEquipoComplejo();

    public abstract DAOEquipoSencillo getDaoEquipoSencillo();

    public abstract DAOPersona getDaoPersona();

}
