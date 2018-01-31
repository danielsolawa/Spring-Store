package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.exceptions.UserAlreadyExistsException;
import com.danielsolawa.storeauth.exceptions.ValidationConstraintException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({ResourceNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<>("Resource not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationConstraintException.class})
    public ResponseEntity<Object> handleValidationConstraintException(Exception e, WebRequest request){
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(Exception exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NumberFormatException.class})
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>("Wrong path param", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }




}
