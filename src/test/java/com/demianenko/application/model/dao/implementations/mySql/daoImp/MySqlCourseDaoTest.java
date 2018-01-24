package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Course;

public class MySqlCourseDaoTest extends AbstractMySqlDaoTest<Course,Integer>{

    public MySqlCourseDaoTest() {
        dao = MySqlDaoFactory.getInstance().getCourseDao();
    }

    @Override
    protected Integer getId(Course obj) {
        return obj.getId();
    }

    @Override
    protected void setId(Course obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected Course getTestObj() {
        Course course = new Course();
        course.setName("testName");
        return course;
    }

    @Override
    protected void updateObj(Course obj) {
        obj.setName("anotherName");
    }
}