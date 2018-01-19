package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegistrationHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //User user = MySqlDaoFactory.getInstance().getUserDao().find(1);
        //request.getSession().setAttribute("test_data", user.getFirstName());
        LOGGER.debug("Input data: "+request.getParameter("inputFirstName")+ " " +
        request.getParameter("inputSecondName")+" " +
        request.getParameter("inputEmail")+" " +
        request.getParameter("inputPassword"));

        User user = new User();
        user.setFirstName(request.getParameter("inputFirstName"));
        user.setSecondName(request.getParameter("inputSecondName"));
        user.setEmail(request.getParameter("inputEmail"));
        user.setPassword(request.getParameter("inputPassword"));
        try {
            ServiceFactory.getInstance().getRegistrationService().register(user);
        } catch (UserInfoException e) {
            //TODO
        }
        return Pages.USER_PAGE;
    }
}