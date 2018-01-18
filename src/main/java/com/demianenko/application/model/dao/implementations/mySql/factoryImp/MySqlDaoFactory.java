package com.demianenko.application.model.dao.implementations.mySql.factoryImp;

import com.demianenko.application.model.dao.implementations.mySql.daoImp.MySqlUserDao;
import com.demianenko.application.model.dao.interfaces.daoInt.*;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;

public class MySqlDaoFactory implements IDaoFactory {

    private static MySqlDaoFactory instance = new MySqlDaoFactory();
    private MySqlUserDao userDao;

    private MySqlDaoFactory() {
        userDao = new MySqlUserDao();
    }

    public static MySqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public ICourseDao getCourseDao() {
        return null;
    }

    @Override
    public IExamResultDao getExamResultDao() {
        return null;
    }

    @Override
    public ISpecialityDao getSpecialityDao() {
        return null;
    }

    @Override
    public IUniversityDao getUniversityDao() {
        return null;
    }

    @Override
    public IUserDao getUserDao() {
        return userDao;
    }
}
