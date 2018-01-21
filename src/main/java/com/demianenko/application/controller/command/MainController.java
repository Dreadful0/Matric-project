package com.demianenko.application.controller.command;

import com.demianenko.application.controller.util.constants.SessionParameters;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        urlPatterns = {"/Main"}
)
public class MainController extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(MainController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.debug("Main servlet init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        LOGGER.debug("do Get");
        LOGGER.debug("parameter: "+req.getParameter(SessionParameters.COMMAND));
        String commandName = req.getParameter(SessionParameters.COMMAND);
        if(commandName == null) return;
        ICommand command = CommandList.valueOf(commandName).getCommand();
        String redirect = command.execute(req, resp);
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        LOGGER.debug("do Post");
        LOGGER.debug("parameter: "+req.getParameter(SessionParameters.COMMAND));
        String commandName = req.getParameter(SessionParameters.COMMAND);
        if(commandName == null) return;
        ICommand command = CommandList.valueOf(commandName).getCommand();
        String redirect = command.execute(req, resp);
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        //LOGGER.debug("do Delete");
    }
}
