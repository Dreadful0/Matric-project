package com.demianenko.application.controller.command;

import com.demianenko.application.controller.command.implementations.*;

public enum CommandList {
    AUTHORIZATION(new AuthorizationHandler()),
    REGISTRATION(new RegistrationHandler()),
    PERSONAL_INFO(new PersonalInfoHandler()),
    LOGOUT(new LogoutHandler()),
    LOCALIZATION(new LocalizationHandler()),
    REDIRECT(new RedirectHandler());

    private ICommand command;

    private CommandList(ICommand command){
        this.command = command;
    }

    public ICommand getCommand(){
        return command;
    }
}
