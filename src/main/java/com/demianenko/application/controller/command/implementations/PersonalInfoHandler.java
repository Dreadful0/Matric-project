package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.ExamResult;
import com.demianenko.application.model.entities.SpecialityRequest;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Personal info handler
 *
 * returns Users personal info
 */
public class PersonalInfoHandler implements ICommand{

    private final static Logger LOGGER = Logger.getLogger(PersonalInfoHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Show personal info");
        try {
            User user = (User) request.getSession().getAttribute("currentUser");
            List<ExamResult> usersExamResults = ServiceFactory.getInstance().getExamService()
                    .getVerifiedUsersExamResults(user);
            List<SpecialityRequest> usersSpecialityRequests = ServiceFactory.getInstance()
                    .getSpecialityApplyingService().getUserSpecialityRequests(user);
            request.setAttribute("user", user);
            request.setAttribute("usersExamResultList", usersExamResults);
            request.setAttribute("usersSpecialityRequestList", usersSpecialityRequests);
        } catch (Exception e) {
            return Pages.USER_PAGE_DIRECT_PATH+"?error=cantShowPersonalInfo";
        }
        return Pages.USER_PAGE_DIRECT_PATH;
    }
}
