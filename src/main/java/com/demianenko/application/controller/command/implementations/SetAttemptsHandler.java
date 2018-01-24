package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Set attempts handler
 *
 * Allows admin to add users exam attempts that represents
 * how many courses the user can choose
 */
public class SetAttemptsHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SetExamMarkHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Set exam attempts");
        try {
            ServiceFactory.getInstance().getUserService().setUsersExamAttempts(SessionParameters.EXAM_ATTEMPTS);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=settingAttemptsError";
        }
        return Pages.ADMIN_PAGE;
    }
}
