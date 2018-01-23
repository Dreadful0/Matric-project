package com.demianenko.application.controller.util.constants;

public interface SessionParameters {

    String COMMAND = "command";
    String USER = "currentUser";

    String EMAIL_COOKIE = "email";
    String PASSWORD_COOKIE = "pass";

    int EXAM_ATTEMPTS = 4;
    int SPECIALITY_REQUESTS_LIMIT = 4;
    int COOKIES_MAX_AGE = 60*60*24;

}
