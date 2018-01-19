package com.demianenko.application.controller.services;

import com.demianenko.application.controller.services.implementations.AuthenticationService;
import com.demianenko.application.controller.services.implementations.RegistrationService;

public interface IServiceFactory {

    RegistrationService getRegistrationService();

    AuthenticationService getAuthenticationService();

}
