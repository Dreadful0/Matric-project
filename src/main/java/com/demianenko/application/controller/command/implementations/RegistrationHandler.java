package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Registration handler
 *
 * Allows guest to register in the system
 */
public class RegistrationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegistrationHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("inputFirstName");
        String secondName = request.getParameter("inputSecondName");
        String email = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        LOGGER.debug("Registration: "+firstName+" "+secondName+" "+email);

        if(Str.isEmpty(firstName, secondName, email, password)){
            return Pages.REGISTRATION_PAGE+"?error=voidInputParameters";
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(password);
        try {
            ServiceFactory.getInstance().getRegistrationService().register(user);
        } catch (UserInfoException e) {
            return Pages.REGISTRATION_PAGE+"?error="+e.getMessage();
        }
        return Pages.USER_PAGE;
    }
}