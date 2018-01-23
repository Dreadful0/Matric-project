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
 * Modify course handler
 *
 * Allows admin to modify the course
 */
public class ModifyCourseHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ModifyCourseHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String courseName = request.getParameter("inputCourseName");
        String id = request.getParameter("choosenCourse");
        LOGGER.debug("Modify course "+id+" "+courseName);
        if(Str.isEmpty(id, courseName)){
            return Pages.ADMIN_PAGE+"?error=voidInputParameters";
        }
        try {
            Course course = new Course();
            course.setId(Integer.parseInt(id));
            course.setName(courseName);
            ServiceFactory.getInstance().getCourseService().modifyCourse(course);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"?error=modifyCourseError";
        }
        return Pages.ADMIN_PAGE;
    }
}
