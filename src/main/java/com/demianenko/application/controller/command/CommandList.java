package com.demianenko.application.controller.command;

import com.demianenko.application.controller.command.implementations.AuthorizationHandler;

public enum CommandList {
    AUTHORIZATION(new AuthorizationHandler());

    private ICommand command;

    private CommandList(ICommand command){
        this.command = command;
    }

    public ICommand getCommand(){
        return command;
    }
}
