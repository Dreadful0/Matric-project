package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LogoutHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory.getInstance().getAuthenticationService().logout(request.getSession());
        return Pages.LOGIN_PAGE;
    }
}
