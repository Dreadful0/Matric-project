package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddSpecialityHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AddSpecialityHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chosenUniversity = request.getParameter("chosenUniversity2");
        String specialityName = request.getParameter("inputSpecialityName");
        String numberOfStudents = request.getParameter("inputSpecialityStudentsNumber");
        String[] coursesId = request.getParameterValues("inputSpecialityCoursesId");
        List<Integer> coursesIdList = Arrays.stream(coursesId)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        LOGGER.debug("Add speciality "+chosenUniversity+" "+specialityName+" "
                +numberOfStudents+" "+coursesIdList);
        Speciality speciality = new Speciality();
        speciality.setName(specialityName);
        speciality.setUniversityId(Integer.parseInt(chosenUniversity));
        speciality.setStudentsNumber(Integer.parseInt(numberOfStudents));
        ServiceFactory.getInstance().getSpecialityService().addSpeciality(speciality,coursesIdList);
        /*String universityName = request.getParameter("inputUniversityName");
        if(universityName != null && !universityName.equals("")){
            University university = new University();
            university.setName(universityName);
            ServiceFactory.getInstance().getUniversityService().addUniversity(university);
        } else{
            LOGGER.debug("University name is null or void");
            //TODO
        }*/
        return Pages.ADMIN_PAGE;
    }
}
