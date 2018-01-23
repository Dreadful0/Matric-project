package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetExamMarkHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SetExamMarkHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mark = request.getParameter("inputMark");
        String examResultId = request.getParameter("chosenExamResult");
        LOGGER.debug("Set exam result "+examResultId+" "+mark);
        ServiceFactory.getInstance().getExamService().setMark(Integer.parseInt(mark),Integer.parseInt(examResultId));
        return Pages.ADMIN_PAGE;
    }
}
