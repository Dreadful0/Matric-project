package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Course;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add course Handler
 *
 * Allows admin to add new course
 */
public class AddCourseHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AddCourseHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        LOGGER.debug("Add course "+request.getParameter("inputCourseName"));
        String courseName = request.getParameter("inputCourseName");
        if(Str.isEmpty(courseName)){
            return Pages.ADMIN_PAGE+"error=voidInputParameters";
        }
        try {
            Course course = new Course();
            course.setName(courseName);
            ServiceFactory.getInstance().getCourseService().addCourse(course);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=addingCourseError";
        }
        return Pages.ADMIN_PAGE;
    }
}
