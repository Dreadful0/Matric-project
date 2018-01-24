package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete course handler
 *
 * Allows admin to delete course
 */
public class DeleteCourseHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(DeleteCourseHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String chosenCourse = request.getParameter("choosenCourse");
        if(Str.isEmpty(chosenCourse)){
            return Pages.ADMIN_PAGE+"error=voidInputParameters";
        }
        LOGGER.debug("Delete course "+chosenCourse);
        try {
            ServiceFactory.getInstance().getCourseService().deleteCourse(Integer.parseInt(chosenCourse));
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"error=deletingCourseError";
        }
        return Pages.ADMIN_PAGE;
    }
}
