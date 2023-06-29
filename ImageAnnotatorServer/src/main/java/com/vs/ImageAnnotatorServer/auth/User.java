package com.vs.ImageAnnotatorServer.auth;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;


    @Column(unique = true)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(name="first_name")
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name="last_name")
    @NotNull
    @NotBlank
    private String lastName;

    @Column
    @NotNull
    @NotBlank
    @JsonIgnore
    private String password;


    @Column
    @NotNull
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    @NotEmpty
    @NotNull
    private Set<Authority> authorities;

    public User(){}

    public User(@NotNull String email, @NotNull String password, @NotNull String firstName, @NotNull String lastName) {
        this.email = email;
        this.password = password;
        this.firstName= firstName;
        this.lastName = lastName;
        this.enabled = true;
        this.authorities = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return email;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    public void addRole(String role){
        Authority authority = new Authority("ROLE_" + role);
        this.authorities.add(authority);
    }


    public void addAuthority(String authority){
        Authority auth = new Authority(authority);
        this.authorities.add(auth);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return this.email.equals(((User) obj).email);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password=' [Protected]" + '\'' +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}
