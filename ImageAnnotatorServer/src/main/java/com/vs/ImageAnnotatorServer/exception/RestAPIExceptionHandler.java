package com.vs.ImageAnnotatorServer.exception;

import com.vs.ImageAnnotatorServer.rest.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestAPIExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestResponse> handleAuthenticationException(UserCreationException e){
        RestResponse restResponse = new RestResponse(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restResponse);
    }

}
