package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout handler
 *
 * Allows User to log out
 */
public class LogoutHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LogoutHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getAuthenticationService().logout(request.getSession());
            Cookie emailCookie = new Cookie(SessionParameters.EMAIL_COOKIE, null);
            Cookie passCookie = new Cookie(SessionParameters.PASSWORD_COOKIE, null);
            emailCookie.setMaxAge(0);
            passCookie.setMaxAge(0);
            response.addCookie(emailCookie);
            response.addCookie(passCookie);
        } catch (Exception e) {
            return Pages.LOGIN_PAGE+"error=logoutError";
        }
        return Pages.LOGIN_PAGE;
    }
}
