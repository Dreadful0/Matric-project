package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

/**
 * Groups business logic for managing users
 */
public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private IDaoFactory daoFactory;

    public UserService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Performs deleting user by id
     *
     * @param id User id
     */
    public void deleteUser(int id){
        daoFactory.getUserDao().delete(id);
    }

    /**
     * Performs modifying User
     *
     * @param user User
     */
    public  void modifyUser(User user){
        daoFactory.getUserDao().update(user);
    }

    /**
     * Performs changing User role by email
     *
     * @param email User email
     * @param role new Role
     */
    public void changeUserRole(String email, String role){
        User user = daoFactory.getUserDao().findByEmail(email);
        user.setRole(Role.getRole(role));
        daoFactory.getUserDao().update(user);
    }

    /**
     * Performs setting users exam attempts that represents
     * how many courses the user can choose for the exam
     *
     * @param attempts Integer attempts number
     */
    public void setUsersExamAttempts(Integer attempts){
        daoFactory.getUserDao().setAllUsersExamAttempts(attempts);
    }
}
