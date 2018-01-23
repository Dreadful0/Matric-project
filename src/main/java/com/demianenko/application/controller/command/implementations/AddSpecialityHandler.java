package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Add speciality handler
 *
 * Allows admin to add speciality
 */
public class AddSpecialityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AddSpecialityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String chosenUniversity = request.getParameter("chosenUniversity2");
        String specialityName = request.getParameter("inputSpecialityName");
        String numberOfStudents = request.getParameter("inputSpecialityStudentsNumber");
        String[] coursesId = request.getParameterValues("inputSpecialityCoursesId");
        LOGGER.debug("Add speciality "+chosenUniversity+" "+specialityName+" "
                +numberOfStudents);
        if(Str.isEmpty(chosenUniversity,specialityName,numberOfStudents) || coursesId.length==0){
            return Pages.ADMIN_PAGE+"?error=voidInputParameters";
        }
        try {
            List<Integer> coursesIdList = Arrays.stream(coursesId)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            Speciality speciality = new Speciality();
            speciality.setName(specialityName);
            speciality.setUniversityId(Integer.parseInt(chosenUniversity));
            speciality.setStudentsNumber(Integer.parseInt(numberOfStudents));
            ServiceFactory.getInstance().getSpecialityService().addSpeciality(speciality,coursesIdList);
        } catch (UserInfoException e) {
            return Pages.ADMIN_PAGE+"?error="+e.getMessage();
        } catch (Exception e){
            return Pages.ADMIN_PAGE+"?error=addSpecialityError";
        }
        return Pages.ADMIN_PAGE;
    }
}
