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
 * Apply for speciality Handler
 *
 * Allows user to apply for Speciality
 */
public class ApplyForSpecialityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ApplyForSpecialityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("Apply for speciality");
        try {
            String[] examResultsId = request.getParameterValues("inputExamResultsId");
            List<Integer> examResultIdList = Arrays.stream(examResultsId)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            String chosenSpecialityId = request.getParameter("chosenSpeciality");
            LOGGER.debug("Speciality id "+chosenSpecialityId+"Exam results id "+examResultIdList);
            try {
                ServiceFactory.getInstance().getSpecialityApplyingService()
                        .applyForSpeciality(Integer.parseInt(chosenSpecialityId), examResultIdList,
                                (User)request.getSession().getAttribute(SessionParameters.USER));
            } catch (UserInfoException e) {
                return Pages.SPECIALITIES_PAGE+"error="+e.getMessage();
            }
        } catch (Exception e) {
            return Pages.SPECIALITIES_PAGE+"error=applyingForSpecialityError";
        }
        return Pages.SPECIALITIES_PAGE;
    }
}
