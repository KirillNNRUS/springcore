package com.medoshin.lectures.javalab.springcore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/controller-advice-exception-handling.html
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String processExceptions(Exception exception) {
        System.out.println("---------from controller advice: " + exception);
        return "Error from controller advice";
    }
}
