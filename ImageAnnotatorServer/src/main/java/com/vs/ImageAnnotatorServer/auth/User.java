package com.vs.ImageAnnotatorServer.entities;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @Column(name="user_name", length = 50, unique = true)
    @Size(min = 6, max = 50)
    @NotNull
    private String username;

    @Column(name="email", unique = true)
    @NotNull
    private String email;

    @Column(name="password")
    @Size(min = 6, max = 18)
    @NotNull
    private String password;

    @Column(name="role")
    @NotNull
    private String role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
