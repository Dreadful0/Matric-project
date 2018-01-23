package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete university handler
 *
 * Allows admin to delete university
 */
public class DeleteUniversityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteUniversityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String chosenUniversity = request.getParameter("chosenUniversity");
        LOGGER.debug("Delete university "+chosenUniversity);
        if(Str.isEmpty(chosenUniversity)){
            return Pages.ADMIN_PAGE+"?error=voidInputParameters";
        }
        try {
            ServiceFactory.getInstance().getUniversityService().deleteUniversity(Integer.parseInt(chosenUniversity));
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"?error=deletingUniversityError";
        }
        return Pages.ADMIN_PAGE;
    }
}
