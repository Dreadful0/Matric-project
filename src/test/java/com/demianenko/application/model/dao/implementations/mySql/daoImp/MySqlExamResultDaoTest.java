package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.ExamResult;

public class MySqlExamResultDaoTest extends AbstractMySqlDaoTest<ExamResult,Integer>{

    public MySqlExamResultDaoTest() {
        dao = MySqlDaoFactory.getInstance().getExamResultDao();
    }

    @Override
    protected Integer getId(ExamResult obj) {
        return obj.getId();
    }

    @Override
    protected void setId(ExamResult obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected ExamResult getTestObj() {
        ExamResult examResult = new ExamResult();
        examResult.setDate(null);
        examResult.setMark(100);
        examResult.setCourseId(1);
        examResult.setUserId(1);
        return examResult;
    }

    @Override
    protected void updateObj(ExamResult obj) {
        obj.setMark(200);
    }
}