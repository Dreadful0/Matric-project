package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Course;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Course registration page info handler
 *
 * Return data for displaying exam registration page
 */
public class CourseRegistrationPageInfoHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(CourseRegistrationPageInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Show course registration page");
        try {
            List<Course> courses = ServiceFactory.getInstance().getCourseService().getAllCourses();
            request.setAttribute("coursesList", courses);
        } catch (Exception e) {
            return Pages.COURSES_PAGE_DIRECT_PATH+"error=cantShowCourseRegistrationPageInfo";
        }
        return Pages.COURSES_PAGE_DIRECT_PATH;
    }
}
