package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Deprecated
public class RedirectHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RedirectHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            return (String) Pages.class.getField(request.getParameter("redirectPage")).get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("Invalid page "+request.getParameter("redirectPage"),e);
        }
        return Pages.INDEX;
    }
}
