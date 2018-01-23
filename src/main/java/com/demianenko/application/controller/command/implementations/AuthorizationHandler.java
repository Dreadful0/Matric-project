package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authorization Handler command
 *
 * allows user to login in system
 */
public class AuthorizationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AuthorizationHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String remember = request.getParameter("inputRemember");
        String email = request.getParameter("inputEmail");
        String pass = request.getParameter("inputPassword");
        LOGGER.debug("Authorization: remember: "+ remember+" email: " + email);
        if(Str.isEmpty(email, pass)){
            return Pages.LOGIN_PAGE+"?error=voidInputParameters";
        }
        try {
            ServiceFactory.getInstance().getAuthenticationService().login(request.getSession(), email, pass);
        } catch (UserInfoException e) {
            return Pages.LOGIN_PAGE+"?error="+e.getMessage();
        }
        if("on".equals(remember)) {
            Cookie emailCookie = new Cookie("email", email);
            Cookie passCookie = new Cookie("pass",
                    ((User) request.getSession().getAttribute(SessionParameters.USER)).getPassword());

            emailCookie.setMaxAge(SessionParameters.COOKIES_MAX_AGE);
            passCookie.setMaxAge(SessionParameters.COOKIES_MAX_AGE);
            response.addCookie( emailCookie );
            response.addCookie( passCookie );
        }
        return Pages.USER_PAGE;
    }

}
