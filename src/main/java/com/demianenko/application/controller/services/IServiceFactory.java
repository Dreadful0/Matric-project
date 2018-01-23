package com.demianenko.application.controller.services;

import com.demianenko.application.controller.services.implementations.*;

public interface IServiceFactory {

    RegistrationService getRegistrationService();

    AuthenticationService getAuthenticationService();

    CourseService getCourseService();

    UniversityService getUniversityService();

    SpecialityService getSpecialityService();

    UserService getUserService();

    ExamService getExamService();

    SpecialityApplyingService getSpecialityApplyingService();

}
