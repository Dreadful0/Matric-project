package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.services.IServiceFactory;
import com.demianenko.application.controller.util.converters.IPasswordEncoder;
import com.demianenko.application.controller.util.converters.Sha256Encoder;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import org.apache.log4j.Logger;

/**
 * Singleton Service factory that used by Handlers to access the services
 */
public class ServiceFactory implements IServiceFactory {

    private final static Logger LOGGER = Logger.getLogger(ServiceFactory.class);

    private static ServiceFactory instance;

    private RegistrationService registrationService;
    private AuthenticationService authenticationService;
    private CourseService courseService;
    private UniversityService universityService;
    private SpecialityService specialityService;
    private UserService userService;
    private ExamService examService;
    private SpecialityApplyingService specialityApplyingService;

    /**
     * Performs initialization of Service factory
     *
     * @param daoFactory factory that performs access to DAO
     * @param passwordEncoder encoder that will be used to save password in database
     */
    public static void init(IDaoFactory daoFactory, IPasswordEncoder passwordEncoder){
        instance = new ServiceFactory(daoFactory, passwordEncoder);
    }

    public ServiceFactory(IDaoFactory daoFactory, IPasswordEncoder passwordEncoder) {
        registrationService = new RegistrationService(daoFactory, passwordEncoder);
        authenticationService = new AuthenticationService(daoFactory, passwordEncoder);
        courseService = new CourseService(daoFactory);
        universityService = new UniversityService(daoFactory);
        specialityService = new SpecialityService(daoFactory);
        userService = new UserService(daoFactory);
        examService = new ExamService(daoFactory);
        specialityApplyingService = new SpecialityApplyingService(daoFactory);
    }

    /**
     * If instance was not created manually creates instance with MySqlDaoFactory and
     * Sha256Encoder
     *
     * @see MySqlDaoFactory
     * @see Sha256Encoder
     *
     * @return instance of Service factory
     */
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

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public ExamService getExamService() {
        return examService;
    }

    @Override
    public SpecialityApplyingService getSpecialityApplyingService() {
        return specialityApplyingService;
    }
}
