package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;

public interface IUserDao extends IGenericDao<User, Integer> {

    Role readRole(Long userId);

}
