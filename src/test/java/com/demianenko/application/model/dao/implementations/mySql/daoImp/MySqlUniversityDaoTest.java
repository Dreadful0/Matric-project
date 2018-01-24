package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.University;

public class MySqlUniversityDaoTest extends AbstractMySqlDaoTest<University,Integer>{

    public MySqlUniversityDaoTest() {
        dao = MySqlDaoFactory.getInstance().getUniversityDao();
    }

    @Override
    protected Integer getId(University obj) {
        return obj.getId();
    }

    @Override
    protected void setId(University obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected University getTestObj() {
        University university = new University();
        university.setName("testName");
        return university;
    }

    @Override
    protected void updateObj(University obj) {
        obj.setName("anotherTestNAme");
    }
}