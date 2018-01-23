package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        String remember = request.getParameter("inputRemember");
        String email = request.getParameter("inputEmail");
        String pass = request.getParameter("inputPassword");
        LOGGER.debug("Input data: "+ remember+" " +
                email+" " +
                pass);
        try {
            ServiceFactory.getInstance().getAuthenticationService().login(request.getSession(),
                    email,
                    pass);
        } catch (UserInfoException e) {
            //TODO
        }
        if("on".equals(remember)) {
            Cookie emailCookie = new Cookie("email", email);
            Cookie passCookie = new Cookie("pass",
                    ((User) request.getSession().getAttribute(SessionParameters.USER)).getPassword());

            emailCookie.setMaxAge(60*60*24);
            passCookie.setMaxAge(60*60*24);

            response.addCookie( emailCookie );
            response.addCookie( passCookie );
        }
        return Pages.USER_PAGE;
    }

}
