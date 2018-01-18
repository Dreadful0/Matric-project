package com.demianenko.application.controller.command;

import com.demianenko.application.controller.util.Pages;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.User;
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
        System.out.println("qwertyui");
        LOGGER.debug("ascdvfdbgnfh");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        LOGGER.debug("do Get");
        User user = MySqlDaoFactory.getInstance().getUserDao().find(1);
        req.setAttribute("test_data", user.getFirstName());
        //LOGGER.debug(user.toString());
        resp.sendRedirect(Pages.INDEX_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //LOGGER.debug("do Post");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        //LOGGER.debug("do Delete");
    }
}
