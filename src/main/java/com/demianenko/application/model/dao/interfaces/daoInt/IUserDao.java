package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.User;

public interface IUserDao extends IGenericDao<User, Integer> {

    void setAllUsersExamAttempts(Integer examAttempts);

    User findByEmail(String eMail);

}
