package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin page info Handler
 *
 * Returns info for the admin page
 */
public class AdminPageInfoHandler implements ICommand{

    private final static Logger LOGGER = Logger.getLogger(AdminPageInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        LOGGER.debug("Show admin page");
        try {
            List<Course> courses = ServiceFactory.getInstance().getCourseService().getAllCourses();
            request.setAttribute("coursesList", courses);
            List<University> universities = ServiceFactory.getInstance()
                    .getUniversityService().getAllUniversitiesWithSpecialities();
            request.setAttribute("universitiesList", universities);
            Map<Integer, List<ExamResult>> examResultsMap = new HashMap<>();
            for (Course course: courses) {
                examResultsMap.put(course.getId(), ServiceFactory.getInstance().getExamService()
                        .getNotVerifiedExamResultsByCourse(course.getId()));
            }
            request.setAttribute("examResultsMap", examResultsMap);
        } catch (Exception e) {
            return Pages.ADMIN_PAGE_DIRECT_PATH+"?error=cantShowAdminPageInfo";
        }
        return Pages.ADMIN_PAGE_DIRECT_PATH;
    }
}
