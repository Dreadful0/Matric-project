package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyUniversityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ModifyUniversityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String universityName = request.getParameter("inputUniversityName");
        String id = request.getParameter("chosenUniversity");
        LOGGER.debug("Modify university "+id+" "+universityName);
        if(id == null || id.equals("")){
            //TODO
            return Pages.ADMIN_PAGE;
        }
        if(universityName != null && !universityName.equals("")){
            University university = new University();
            university.setId(Integer.parseInt(id));
            university.setName(universityName);
            ServiceFactory.getInstance().getUniversityService().modifyUniversity(university);
        } else{
            LOGGER.debug("University name is null or void");
            //TODO
        }
        return Pages.ADMIN_PAGE;
    }
}
