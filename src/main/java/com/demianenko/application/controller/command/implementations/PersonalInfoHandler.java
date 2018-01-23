package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.SpecialityRequest;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonalInfoHandler implements ICommand{

    private final static Logger LOGGER = Logger.getLogger(PersonalInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Show personal info");
        User user = (User) request.getSession().getAttribute("currentUser");
        List<ExamResult> usersExamResults = ServiceFactory.getInstance().getExamService()
                .getVerifiedUsersExamResults(user);
        List<SpecialityRequest> usersSpecialityRequests = ServiceFactory.getInstance()
                .getSpecialityApplyingService().getUserSpecialityRequests(user);
        request.setAttribute("user", user);
        request.setAttribute("usersExamResultList", usersExamResults);
        request.setAttribute("usersSpecialityRequestList", usersSpecialityRequests);
        return Pages.USER_PAGE_DIRECT_PATH;
    }
}
