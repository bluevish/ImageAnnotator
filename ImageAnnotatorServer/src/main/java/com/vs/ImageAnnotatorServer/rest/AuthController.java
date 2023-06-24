package com.vs.ImageAnnotatorServer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/login")
    public ResponseEntity<RestResponse> login(Authentication authentication){
        RestResponse restResponse = new RestResponse(HttpStatus.OK.value(), "Authentication Token");
        restResponse.addData("token",createToken(authentication));
        return new ResponseEntity<>(restResponse, HttpStatus.OK);
    }

    private String createToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer("SELF")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*15))
                .subject(authentication.getName())
                .claim("scope", createScopes(authentication))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    }

    private List<String> createScopes(Authentication authentication) {
        return authentication
                .getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
    }

}
