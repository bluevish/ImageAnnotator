package com.vs.ImageAnnotatorServer.auth;

import com.vs.ImageAnnotatorServer.exception.UserCreationException;
import com.vs.ImageAnnotatorServer.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



// TODO Proper error handling, check for security smells and follow best practices
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RestResponse> login(@RequestBody AuthRequest request){
        RestResponse restResponse = new RestResponse(HttpStatus.OK.value(), "Authentication Token");
        restResponse.addData("token", authService.loginAndGetToken(request));
        return ResponseEntity.ok(restResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse> register(@RequestBody AuthRequest request) throws UserCreationException {
        RestResponse restResponse = new RestResponse(HttpStatus.OK.value(), "Authentication Token");
        restResponse.addData("token", authService.registerAndGetToken(request));
        return ResponseEntity.ok(restResponse);
    }



}
