package com.vs.ImageAnnotatorServer.dao;

import com.vs.ImageAnnotatorServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
