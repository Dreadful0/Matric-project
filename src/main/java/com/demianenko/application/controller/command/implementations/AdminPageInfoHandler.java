package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminPageInfoHandler implements ICommand{

    private final static Logger LOGGER = Logger.getLogger(AdminPageInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Show admin page");
        List<Course> courses = ServiceFactory.getInstance().getCourseService().getAllCourses();
        request.setAttribute("coursesList", courses);
        List<University> universities = ServiceFactory.getInstance()
                .getUniversityService().getAllUniversitiesWithSpecialities();
        LOGGER.debug(universities.toString());
        request.setAttribute("universitiesList", universities);
        return Pages.ADMIN_PAGE_DIRECT_PATH;
    }
}
