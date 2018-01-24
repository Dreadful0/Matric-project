package com.demianenko.application.app;

import com.demianenko.application.controller.command.CommandList;
import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.SecurityConstraints;
import com.demianenko.application.controller.util.converters.Sha256Encoder;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    private final static Logger LOGGER = Logger.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App initializing");
        appInit();
        LOGGER.debug("App initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App destroyed");
    }

    private void appInit(){
        MySQLConnectionFactory.initFromProperties("db");
        MySqlDaoFactory.init(MySQLConnectionFactory.getInstance());
        ServiceFactory.init(MySqlDaoFactory.getInstance(), new Sha256Encoder());
        initSecurityConstraints();
    }

    private void initSecurityConstraints(){
        SecurityConstraints constraints = SecurityConstraints.getInstance();
        constraints.addConstraint(CommandList.PERSONAL_INFO.name(), Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.LOGOUT.name(), Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.ADMIN_PAGE_INFO.name(), Role.ADMIN);
        constraints.addConstraint(CommandList.ADD_COURSE.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.DELETE_COURSE.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.MODIFY_COURSE.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.ADD_UNIVERSITY.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.DELETE_UNIVERSITY.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.MODIFY_UNIVERSITY.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.ADD_SPECIALITY.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.DELETE_SPECIALITY.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.CHANGE_ROLE.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.COURSE_REGISTRATION_PAGE_INFO.name(),Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.SPECIALITY_REGISTRATION_PAGE_INFO.name(),Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.REGISTER_FOR_EXAM.name(),Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.SET_MARK.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.APPLY_FOR_SPECIALITY.name(),Role.USER,Role.ADMIN);
        constraints.addConstraint(CommandList.SET_EXAM_ATTEMPTS.name(),Role.ADMIN);
        constraints.addConstraint(CommandList.PROCESS_RATINGS.name(),Role.ADMIN);
    }
}
