package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Register for exam handler
 *
 * Allows user to register for exams
 */
public class RegisterForExamHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegisterForExamHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Integer> coursesIdList = null;
        User user = null;
        try {
            String[] coursesId = request.getParameterValues("chosenCourses");
            coursesIdList = Arrays.stream(coursesId)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            user = (User)request.getSession().getAttribute(SessionParameters.USER);
        } catch (Exception e) {
            return Pages.COURSES_PAGE+"?error=voidInputParameters";
        }
        LOGGER.debug("Register for exams "+coursesIdList+" "+user);
        if(user == null){
            return Pages.LOGIN_PAGE;
        }
        if(coursesIdList!=null && coursesIdList.size()>user.getExamAttempts()){
            return Pages.COURSES_PAGE+"?error=maxCourseRequests";
        }
        try {
            ServiceFactory.getInstance().getExamService().registerForExams(coursesIdList, user);
        } catch (UserInfoException e) {
            return Pages.COURSES_PAGE+"?error="+e.getMessage();
        } catch (Exception e){
            return Pages.COURSES_PAGE+"?error=registerForExamError";
        }
        return Pages.COURSES_PAGE;
    }
}
