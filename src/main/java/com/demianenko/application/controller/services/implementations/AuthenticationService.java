package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.util.converters.IPasswordEncoder;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * Groups business logic for authentication
 */
public class AuthenticationService {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationService.class);

    private IPasswordEncoder encoder;
    private IDaoFactory daoFactory;

    public AuthenticationService(IDaoFactory daoFactory, IPasswordEncoder encoder) {
        this.encoder = encoder;
        this.daoFactory = daoFactory;
    }

    /**
     * Performs user login
     *
     * @param session HttpSession
     * @param email User email
     * @param password raw user password
     * @throws UserInfoException if email or password are invalid
     */
    public void login(HttpSession session, String email, String password) throws UserInfoException {
        User user = validate(email, encoder.encode(password));
        if(user == null) throw new UserInfoException("invalidEmailOrPassword");
        session.setAttribute("currentUser", user);
    }

    /**
     * Performs user logout
     *
     * @param session HttpSession
     */
    public void logout(HttpSession session){
        session.invalidate();
    }

    /**
     * Performs user validation
     *
     * @param email user email
     * @param password encoded user password
     * @return valid User entity
     */
    public User validate(String email, String password){
        LOGGER.debug("validating user");
        if(email == null || password == null){
            return null;
        }
        User founded = daoFactory.getUserDao().findByEmail(email);
        if(founded == null) {
            LOGGER.debug("User not found");
            return null;
        }
        if(!founded.getPassword().equals(password)){
            LOGGER.debug("wrong password");
            return null;
        }
        LOGGER.debug("user is valid");
        return founded;
    }
}
