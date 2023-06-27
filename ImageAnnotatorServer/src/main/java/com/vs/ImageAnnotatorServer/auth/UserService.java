package com.vs.ImageAnnotatorServer.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Username does not exists.");
        }
        return user;
    }

    public void createUser(String email, String password, String role){
        Authority authority = new Authority(USER_ROLE.valueOf(role));
        User user = new User(email, passwordEncoder.encode(password),true);
        user.addRole(authority);
        userRepository.save(user);
    }


}
