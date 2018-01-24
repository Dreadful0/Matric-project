package com.demianenko.application.controller.util.constants;

public interface Pages {

    String INDEX = "jsp/index.jsp?";
    String REGISTRATION_PAGE = "jsp/registration.jsp?";
    String LOGIN_PAGE = "jsp/login.jsp?";
    String USER_PAGE = "/Main?command=PERSONAL_INFO&";
    String USER_PAGE_DIRECT_PATH = "/jsp/userPage.jsp?";
    String ADMIN_PAGE = "/Main?command=ADMIN_PAGE_INFO&";
    String ADMIN_PAGE_DIRECT_PATH = "/jsp/adminPage.jsp?";
    String COURSES_PAGE = "/Main?command=COURSE_REGISTRATION_PAGE_INFO&";
    String COURSES_PAGE_DIRECT_PATH = "/jsp/courseRegistration.jsp?";
    String SPECIALITIES_PAGE = "/Main?command=SPECIALITY_REGISTRATION_PAGE_INFO&";
    String SPECIALITIES_PAGE_DIRECT_PATH = "/jsp/specialityRegistration.jsp?";
    String ACCESS_DENIED = "jsp/errorPages/403accessDenied.jsp?";

}
