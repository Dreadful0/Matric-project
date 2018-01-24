package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.SpecialityRequest;

public class MySqlSpecialityRequestDaoTest extends AbstractMySqlDaoTest<SpecialityRequest, Integer>{

    public MySqlSpecialityRequestDaoTest() {
        dao = MySqlDaoFactory.getInstance().getSpecialityRequestDao();
    }

    @Override
    protected Integer getId(SpecialityRequest obj) {
        return obj.getId();
    }

    @Override
    protected void setId(SpecialityRequest obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected SpecialityRequest getTestObj() {
        SpecialityRequest specialityRequest = new SpecialityRequest();
        specialityRequest.setSpecialityId(5);
        specialityRequest.setUserId(1);
        specialityRequest.setFinalMark(100);
        specialityRequest.setConfirmed("confirmed");
        return specialityRequest;
    }

    @Override
    protected void updateObj(SpecialityRequest obj) {
        obj.setFinalMark(200);
    }
}