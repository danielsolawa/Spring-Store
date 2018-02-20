package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.exceptions.*;
import com.danielsolawa.storeauth.utils.ErrorInfo;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({ResourceNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationConstraintException.class})
    public ResponseEntity<Object> handleValidationConstraintException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ActivateTokenExpiredException.class})
    public ResponseEntity<Object> handleActivateTokenExpiredException(Exception exception, WebRequest request){

        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.GONE);
    }

    @ExceptionHandler({TokenMismatchException.class})
    public ResponseEntity<Object> handleTokenMismatchException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccountAlreadyActivatedException.class})
    public ResponseEntity<Object> handleAccountAlreadyActivatedException(Exception exception, WebRequest request){
        return new ResponseEntity<>(ErrorInfo.builder().message(exception.getMessage()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class})
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorInfo.builder().message(ex.getMessage()).build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }






}
