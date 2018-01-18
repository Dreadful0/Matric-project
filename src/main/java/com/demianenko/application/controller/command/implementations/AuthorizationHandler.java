package com.demianenko.application.controller.command.implementations;

import com.demianenko.application.controller.command.ICommand;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.demianenko.application.controller.util.Pages.INDEX_JSP;

public class AuthorizationHandler implements ICommand {

    private final static Logger LOGGER = Logger.getLogger(AuthorizationHandler.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = MySqlDaoFactory.getInstance().getUserDao().find(1);
        request.setAttribute("test_data", user);
        return INDEX_JSP;
    }

}
