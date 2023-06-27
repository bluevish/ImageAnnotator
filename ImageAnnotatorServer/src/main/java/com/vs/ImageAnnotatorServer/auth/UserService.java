package com.vs.ImageAnnotatorServer.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = Logger.getLogger(getClass().toString());

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Username does not exists.");
        }
        return user;
    }

    @Transactional
    public void createUser(String email, String password, String firstName, String lastName, List<String> roles){
        User user = new User(email, password, firstName, lastName);
        for(String role : roles) {
            user.addRole(role);
        }
        userRepository.save(user);
    }


}
