package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Modify university handler
 *
 * Allows Admit to modify university
 */
public class ModifyUniversityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ModifyUniversityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String universityName = request.getParameter("inputUniversityName");
        String id = request.getParameter("chosenUniversity");
        LOGGER.debug("Modify university "+id+" "+universityName);
        if(Str.isEmpty(universityName,id)){
            return Pages.ADMIN_PAGE+"error=voidInputParameters";
        }
        try {
            University university = new University();
            university.setId(Integer.parseInt(id));
            university.setName(universityName);
            ServiceFactory.getInstance().getUniversityService().modifyUniversity(university);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=modifyUniversityError";
        }
        return Pages.ADMIN_PAGE;
    }
}
