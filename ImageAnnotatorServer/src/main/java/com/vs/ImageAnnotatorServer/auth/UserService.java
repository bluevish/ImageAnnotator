package com.vs.ImageAnnotatorServer.auth;

import com.vs.ImageAnnotatorServer.utils.exception.UserCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// TODO : Implement proper password validations
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    private final Logger logger = Logger.getLogger(getClass().toString());

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Username does not exists.");
        }
        return user;
    }

    public void createUser(String email, String password, String firstName, String lastName, List<String> roles, PasswordEncoder encoder) throws UserCreationException {
        try{
            if(password==null || (password.length() < 6 || password.length() > 15)) {
                throw new UserCreationException("Password must be 6 to 15 characters long!");
            }
            User user = new User(email, encoder.encode(password), firstName, lastName);
            if(roles != null){
                for(String role : roles) {
                    user.addRole(role);
                }
            }

            userRepository.save(user);
        }
        catch (ConstraintViolationException | DataIntegrityViolationException e) {
            String message = "";
            if(e instanceof ConstraintViolationException){

                message = ((ConstraintViolationException) e).getConstraintViolations()
                        .stream()
                        .map(cv -> cv.getPropertyPath() + " " + cv.getMessage())
                        .collect(Collectors.joining(","));
            }
            else {
                message = e.getMessage();
            }
            throw new UserCreationException(message);
        }
    }


}
