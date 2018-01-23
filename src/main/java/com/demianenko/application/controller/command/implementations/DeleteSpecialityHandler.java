package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteSpecialityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteSpecialityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chosenSpeciality = request.getParameter("chosenSpeciality");
        LOGGER.debug("Delete speciality "+chosenSpeciality);
        ServiceFactory.getInstance().getSpecialityService().deleteSpeciality(Integer.parseInt(chosenSpeciality));
        return Pages.ADMIN_PAGE;
    }
}
