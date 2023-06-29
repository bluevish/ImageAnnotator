package com.vs.ImageAnnotatorServer.utils.exception;

import com.vs.ImageAnnotatorServer.rest.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestResponse> handleException(UserCreationException e){
        RestResponse restResponse = new RestResponse(HttpStatus.CONFLICT.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restResponse);
    }

    @ExceptionHandler
    public ResponseEntity<RestResponse> handleException(AuthenticationException e){
        RestResponse restResponse = new RestResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restResponse);
    }



}
