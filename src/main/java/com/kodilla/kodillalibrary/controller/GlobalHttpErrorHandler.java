package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception) {
        return new ResponseEntity<>("Object with given id (or required PARAMS) doesn't exist", HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ObjectNotFoundException.class)
//    public ResponseEntity<Object> handleStatus500(ObjectNotFoundException exception) {
//        return new ResponseEntity<>(("Wrong parameters. Object(s) doesn't exist"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
