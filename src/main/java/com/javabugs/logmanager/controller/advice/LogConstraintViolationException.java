package com.javabugs.logmanager.controller.advice;

public class LogConstraintViolationException extends RuntimeException {

    public LogConstraintViolationException(String constraint) {
        super(constraint + " not found");
    }

}
