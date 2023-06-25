package com.vs.ImageAnnotatorServer.auth;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserRole {



    @Column(name="role")
    @NotNull
    private USER_ROLE role;

}
