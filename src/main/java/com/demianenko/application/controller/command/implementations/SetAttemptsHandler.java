package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetAttemptsHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SetExamMarkHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Set exam attempts");
        ServiceFactory.getInstance().getUserService().setUsersExamAttempts(SessionParameters.EXAM_ATTEMPTS);
        return Pages.ADMIN_PAGE;
    }
}
