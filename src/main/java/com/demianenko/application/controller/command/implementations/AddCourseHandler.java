package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Course;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCourseHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AddCourseHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Add course "+request.getParameter("inputCourseName"));
        String courseName = request.getParameter("inputCourseName");
        if(courseName != null && !courseName.equals("")){
            Course course = new Course();
            course.setName(courseName);
            ServiceFactory.getInstance().getCourseService().addCourse(course);
        } else{
            LOGGER.debug("Course name is null or void");
            //TODO
        }
        return Pages.ADMIN_PAGE;
    }
}
