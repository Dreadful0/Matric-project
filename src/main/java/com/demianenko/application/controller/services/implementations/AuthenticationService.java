package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.util.converters.IPasswordEncoder;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

public class AuthenticationService {

    private final static Logger LOGGER = Logger.getLogger(AuthenticationService.class);

    private IPasswordEncoder encoder;
    private IDaoFactory daoFactory;

    public AuthenticationService(IDaoFactory daoFactory, IPasswordEncoder encoder) {
        this.encoder = encoder;
        this.daoFactory = daoFactory;
    }

    public void login(HttpSession session, String email, String password) throws UserInfoException {
        User user = validate(email, encoder.encode(password));
        if(user == null) throw new UserInfoException("InvalidEmailOrPassword");
        session.setAttribute("currentUser", user);
    }

    public void logout(HttpSession session){
        session.invalidate();
    }

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
