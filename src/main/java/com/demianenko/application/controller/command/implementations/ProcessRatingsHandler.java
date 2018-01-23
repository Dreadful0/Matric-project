package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.constants.Pages;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process ratings handler
 *
 * Allows admin to start processing ratings
 */
public class ProcessRatingsHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(ProcessRatingsHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        LOGGER.debug("Process speciality requests");
        try {
            ServiceFactory.getInstance().getSpecialityApplyingService().processRatings();
        } catch (UserInfoException e) {
            return Pages.ADMIN_PAGE+"?error="+e.getMessage();
        } catch (Exception e){
            return Pages.ADMIN_PAGE+"?error=processRatingsError";
        }
        return Pages.ADMIN_PAGE;
    }
}
