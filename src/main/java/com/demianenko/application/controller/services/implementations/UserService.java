package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private IDaoFactory daoFactory;

    public UserService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void deleteUser(int id){
        daoFactory.getUserDao().delete(id);
    }

    public  void modifyUser(User user){
        daoFactory.getUserDao().update(user);
    }

    public void changeUserRole(String email, String role){
        User user = daoFactory.getUserDao().findByEmail(email);
        user.setRole(Role.getRole(role));
        daoFactory.getUserDao().update(user);
    }

    public void setUsersExamAttempts(Integer attempts){
        daoFactory.getUserDao().setAllUsersExamAttempts(attempts);
    }
}
