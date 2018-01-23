package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.Str;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Set exam mark handler
 *
 * Allows admin to set mark for exam
 */
public class SetExamMarkHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(SetExamMarkHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String mark = request.getParameter("inputMark");
        String examResultId = request.getParameter("chosenExamResult");
        LOGGER.debug("Set exam result "+examResultId+" "+mark);
        if(Str.isEmpty(mark, examResultId)){
            return Pages.ADMIN_PAGE+"?error=voidInputParameters";
        }
        try {
            ServiceFactory.getInstance().getExamService().setMark(Integer.parseInt(mark),Integer.parseInt(examResultId));
        } catch (Exception e) {
            return Pages.ADMIN_PAGE+"?error=settingExamMarkError";
        }
        return Pages.ADMIN_PAGE;
    }
}
