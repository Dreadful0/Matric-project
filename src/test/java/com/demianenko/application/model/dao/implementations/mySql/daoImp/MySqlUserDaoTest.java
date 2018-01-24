package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;

public class MySqlUserDaoTest extends AbstractMySqlDaoTest<User, Integer>{

    private int counterDifferentEmails = 0;

    public MySqlUserDaoTest(){
        dao = MySqlDaoFactory.getInstance().getUserDao();
    }

    @Override
    protected Integer getId(User obj) {
        return obj.getId();
    }

    @Override
    protected void setId(User obj, Integer id) {
        obj.setId(id);
    }

    @Override
    protected User getTestObj() {
        User user = new User();
        user.setFirstName("testName");
        user.setSecondName("testSecondName");
        user.setEmail("testEmail"+counterDifferentEmails);
        user.setPassword("encodedPassword");
        user.setRole(Role.USER);
        counterDifferentEmails++;
        return user;
    }

    @Override
    protected void updateObj(User obj) {
        obj.setFirstName("anotherTestName");
    }
}