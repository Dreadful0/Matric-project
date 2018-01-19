package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class LocalizationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(LocalizationHandler.class);

    private static final String UA = "uk_UA";
    private static final String EN = "en_EN";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Changing locale");
        String lang = request.getParameter("language");
        if(lang == null) lang = EN;
        switch (lang){
            case UA:
                LOGGER.debug(UA);
                Config.set(request.getSession(),Config.FMT_LOCALE,UA);
                break;
            default:
                LOGGER.debug("default "+EN);
                Config.set(request.getSession(),Config.FMT_LOCALE,EN);
                break;
        }
        //return request.getHeader("referer");
        return Pages.INDEX;
    }
}
