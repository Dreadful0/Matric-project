package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.services.IServiceFactory;
import com.demianenko.application.controller.util.converters.IPasswordEncoder;
import com.demianenko.application.controller.util.converters.Sha256Encoder;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import org.apache.log4j.Logger;

public class ServiceFactory implements IServiceFactory {

    private final static Logger LOGGER = Logger.getLogger(ServiceFactory.class);

    private static ServiceFactory instance;

    private RegistrationService registrationService;
    private AuthenticationService authenticationService;
    private CourseService courseService;
    private UniversityService universityService;
    private SpecialityService specialityService;

    public static void init(IDaoFactory daoFactory, IPasswordEncoder passwordEncoder){
        instance = new ServiceFactory(daoFactory, passwordEncoder);
    }

    public ServiceFactory(IDaoFactory daoFactory, IPasswordEncoder passwordEncoder) {
        registrationService = new RegistrationService(daoFactory, passwordEncoder);
        authenticationService = new AuthenticationService(daoFactory, passwordEncoder);
        courseService = new CourseService(daoFactory);
        universityService = new UniversityService(daoFactory);
        specialityService = new SpecialityService(daoFactory);
    }

    public static ServiceFactory getInstance() {
        if(instance == null) {
            LOGGER.debug("Default init from MySQL DAO factory and sha 256 encoder");
            init(MySqlDaoFactory.getInstance(), new Sha256Encoder());
        }
        return instance;
    }

    @Override
    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    @Override
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    @Override
    public CourseService getCourseService() {
        return courseService;
    }

    @Override
    public UniversityService getUniversityService() {
        return universityService;
    }

    @Override
    public SpecialityService getSpecialityService() {
        return specialityService;
    }
}
