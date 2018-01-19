package com.demianenko.application.controller.exceptions;

public class UserInfoException extends Exception {

    public UserInfoException(String message) {
        super(message);
    }

    public UserInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoException(Throwable cause) {
        super(cause);
    }
}
