package com.ok.dog.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());


    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRandomFailures(IllegalStateException ex) {
        logger.log(Level.SEVERE, ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // error code
                "Internal Server Error: " + ex.getMessage() // error message
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @Data
    @AllArgsConstructor
    public class ErrorResponse {
        private int errorCode;
        private String errorMessage;
    }

}
