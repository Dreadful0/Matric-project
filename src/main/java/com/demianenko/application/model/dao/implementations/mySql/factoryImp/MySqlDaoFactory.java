package com.demianenko.application.model.dao.implementations.mySql.factoryImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.*;
import com.demianenko.application.model.dao.interfaces.daoInt.*;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import org.apache.log4j.Logger;

public class MySqlDaoFactory implements IDaoFactory {

    private final static Logger LOGGER = Logger.getLogger(MySqlDaoFactory.class);

    private static MySqlDaoFactory instance;
    private MySqlUserDao userDao;
    private MySqlCourseDao courseDao;
    private MySqlSpecialityDao specialityDao;
    private MySqlUniversityDao universityDao;
    private MySqlExamResultDao examResultDao;

    private MySqlDaoFactory(IConnectionFactory connectionFactory) {
        userDao = new MySqlUserDao(connectionFactory);
        courseDao = new MySqlCourseDao(connectionFactory);
        specialityDao = new MySqlSpecialityDao(connectionFactory);
        universityDao = new MySqlUniversityDao(connectionFactory);
        examResultDao = new MySqlExamResultDao(connectionFactory);
    }

    public static void init(IConnectionFactory connectionFactory){
        instance = new MySqlDaoFactory(connectionFactory);
    }

    public static MySqlDaoFactory getInstance() {
        if(instance == null) {
            LOGGER.debug("Default init from MySQLConnection factory");
            init(MySQLConnectionFactory.getInstance());
        }
        return instance;
    }

    @Override
    public ICourseDao getCourseDao() {
        return courseDao;
    }

    @Override
    public IExamResultDao getExamResultDao() {
        return examResultDao;
    }

    @Override
    public ISpecialityDao getSpecialityDao() {
        return specialityDao;
    }

    @Override
    public IUniversityDao getUniversityDao() {
        return universityDao;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }
}
