package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Speciality;

public class MySqlSpecialityDaoTest extends AbstractMySqlDaoTest<Speciality,Integer>{

    public MySqlSpecialityDaoTest() {
        dao = MySqlDaoFactory.getInstance().getSpecialityDao();
    }

    @Override
    protected Integer getId(Speciality obj) {
        return obj.getId();
    }

    @Override
    protected void setId(Speciality obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected Speciality getTestObj() {
        Speciality speciality = new Speciality();
        speciality.setName("testName");
        speciality.setUniversityId(1);
        speciality.setStudentsNumber(20);
        return speciality;
    }

    @Override
    protected void updateObj(Speciality obj) {
        obj.setName("anotherTestName");
    }
}