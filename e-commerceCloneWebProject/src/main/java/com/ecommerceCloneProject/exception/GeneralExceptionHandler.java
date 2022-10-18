package com.ecommerceCloneProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> generalExceptionHandler( UserNotFoundException exception){

        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotActiveException.class)
    public ResponseEntity<?> generalExceptionHandler(UserIsNotActiveException userIsNotActiveException){

        return new ResponseEntity<>(userIsNotActiveException.getMessage() , HttpStatus.BAD_REQUEST);
    }






}
