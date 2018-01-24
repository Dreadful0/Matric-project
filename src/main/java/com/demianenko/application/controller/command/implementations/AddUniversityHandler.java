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
 * Add University handler
 *
 * Allows admin to add new university
 */
public class AddUniversityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AddCourseHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String universityName = request.getParameter("inputUniversityName");
        LOGGER.debug("Add university "+universityName);
        if(Str.isEmpty(universityName)){
            return Pages.ADMIN_PAGE+"error=voidInputParameters";
        }
        try {
            University university = new University();
            university.setName(universityName);
            ServiceFactory.getInstance().getUniversityService().addUniversity(university);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=addUniversityError";
        }
        return Pages.ADMIN_PAGE;
    }

}
