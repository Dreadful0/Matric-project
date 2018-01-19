package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonalInfoHandler implements ICommand{

    private final static Logger LOGGER = Logger.getLogger(PersonalInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Show personal info");
        User user = (User) request.getSession().getAttribute("currentUser");
        request.setAttribute("user", user);
        return "/jsp/userPage.jsp";
    }
}
