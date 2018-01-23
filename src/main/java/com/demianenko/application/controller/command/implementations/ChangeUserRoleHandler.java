package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change user role handler
 *
 * Allows admin to change user role
 */
public class ChangeUserRoleHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ChangeUserRoleHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userEmail = request.getParameter("inputEmail");
        String role = request.getParameter("inputRole");
        LOGGER.debug("Change user role "+userEmail+" "+role);
        if(Str.isEmpty(userEmail,role)){
            return Pages.ADMIN_PAGE+"?error=voidInputParameters";
        }
        try {
            ServiceFactory.getInstance().getUserService().changeUserRole(userEmail, role);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"?error=changeUserRoleError";
        }
        return Pages.ADMIN_PAGE;
    }
}
