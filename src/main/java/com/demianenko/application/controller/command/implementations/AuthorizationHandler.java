package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AuthorizationHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //User user = MySqlDaoFactory.getInstance().getUserDao().find(1);
        //request.setAttribute("test_data", user);
        //request.getSession();
        LOGGER.debug("Input data: "+ request.getParameter("inputRemember")+" " +
                request.getParameter("inputEmail")+" " +
                request.getParameter("inputPassword"));
        try {
            ServiceFactory.getInstance().getAuthenticationService().login(request.getSession(),
                    request.getParameter("inputEmail"),
                    request.getParameter("inputPassword"));
        } catch (UserInfoException e) {
            //TODO
        }
        return Pages.USER_PAGE;
    }

}
