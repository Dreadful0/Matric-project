package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.util.converters.IPasswordEncoder;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

public class RegistrationService {

    private final static Logger LOGGER = Logger.getLogger(RegistrationService.class);
    private IPasswordEncoder encoder;
    private IDaoFactory daoFactory;

    public RegistrationService(IDaoFactory daoFactory, IPasswordEncoder encoder) {
        this.daoFactory = daoFactory;
        this.encoder = encoder;
    }

    public boolean register(User user) throws UserInfoException{
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user.setRole(Role.USER);
        user.setEmail(user.getEmail().toLowerCase());

        synchronized (this) {
            User exist = daoFactory.getUserDao().findByEmail(user.getEmail());
            if (exist == null) {
                daoFactory.getUserDao().add(user);
                return true;
            } else {
                LOGGER.debug("Can not add user, email already exist");
                throw new UserInfoException("EmailAlreadyExist");
            }
        }
    }
}
