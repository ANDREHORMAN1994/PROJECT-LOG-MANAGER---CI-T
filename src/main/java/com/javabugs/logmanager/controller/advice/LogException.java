package com.javabugs.logmanager.controller.advice;

public class LogException extends RuntimeException {

    public LogException(String error) {
        super(error);
    }

}
