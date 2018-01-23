package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.University;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpecialityRegistrationPageInfoHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SpecialityRegistrationPageInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Show speciality registration page");
        //List<Course> courses = ServiceFactory.getInstance().getCourseService().getAllCourses();
        //request.setAttribute("coursesList", courses);
        List<University> universities = ServiceFactory.getInstance()
                .getUniversityService().getAllUniversitiesWithSpecialities();
        request.setAttribute("universitiesList", universities);
        List<ExamResult> examResults = ServiceFactory.getInstance().getExamService()
                .getVerifiedUsersExamResults((User)request.getSession().getAttribute(SessionParameters.USER));
        request.setAttribute("userExamResults", examResults);
        LOGGER.debug("User exam results "+examResults.toString());
        return Pages.SPECIALITIES_PAGE_DIRECT_PATH;
    }
}
