package com.tech.challenge.frontend.service.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.frontend.service.model.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandlerController.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e){
        log.error("exception :{}, {}, {}",e.getClass(),e.getMessage(),e.getCause());
        return new ResponseEntity<>(ErrorDTO.builder()
                .error(e.getClass().getName())
                .message(String.format("unexpected exception, message: %s",e.getMessage())).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleWebMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) throws JsonProcessingException {
        log.error("{} invalid value:'{}' for param:'{}'",e.getClass(),e.getValue(),e.getName());
        return new ResponseEntity<>(ErrorDTO.builder()
                .error("BAD REQUEST")
                .message(String.format("invalid value:'%s' for param:'%s'",e.getValue(),e.getName())).build(),
                HttpStatus.BAD_REQUEST);
    }
}
