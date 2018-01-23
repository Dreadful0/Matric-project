package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.controller.util.constants.SessionParameters;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterForExamHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(RegisterForExamHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] coursesId = request.getParameterValues("chosenCourses");
        List<Integer> coursesIdList = Arrays.stream(coursesId)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        User user = (User)request.getSession().getAttribute(SessionParameters.USER);
        LOGGER.debug("Register for exams "+coursesIdList+" "+user);
        if(user == null){

        }
        if(coursesIdList.size()>user.getExamAttempts()){
            //TODO
        }
        try {
            ServiceFactory.getInstance().getExamService().registerForExams(coursesIdList, user);
        } catch (UserInfoException e) {
            LOGGER.error(e);
        }
        return Pages.COURSES_PAGE;
    }
}
