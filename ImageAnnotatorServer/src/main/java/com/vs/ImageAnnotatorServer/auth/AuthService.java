package com.vs.ImageAnnotatorServer.auth;

import com.vs.ImageAnnotatorServer.utils.exception.UserCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private final Logger logger = Logger.getLogger(getClass().toString());

    public String loginAndGetToken(AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return createToken(authentication);
    }

    public String registerAndGetToken(AuthRequest request) throws UserCreationException {
        userService.createUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), request.getRoles(), passwordEncoder);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return createToken(authentication);
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
