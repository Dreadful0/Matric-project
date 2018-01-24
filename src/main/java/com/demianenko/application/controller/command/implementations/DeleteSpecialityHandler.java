package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete speciality handler
 *
 * Allows admin to delete the speciality
 */
public class DeleteSpecialityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteSpecialityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String chosenSpeciality = request.getParameter("chosenSpeciality");
        LOGGER.debug("Delete speciality "+chosenSpeciality);
        if(Str.isEmpty(chosenSpeciality)){
            return Pages.ADMIN_PAGE+"error=voidInputParameters";
        }
        try {
            ServiceFactory.getInstance().getSpecialityService().deleteSpeciality(Integer.parseInt(chosenSpeciality));
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=deleteSpecialityError";
        }
        return Pages.ADMIN_PAGE;
    }
}
