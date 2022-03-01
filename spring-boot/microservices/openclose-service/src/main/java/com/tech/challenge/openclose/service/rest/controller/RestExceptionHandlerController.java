package com.tech.challenge.openclose.service.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandlerController.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e){
        e.printStackTrace();
        log.error("exception :{}, {}, {}",e.getClass(),e.getMessage(),e.getCause());
            return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
