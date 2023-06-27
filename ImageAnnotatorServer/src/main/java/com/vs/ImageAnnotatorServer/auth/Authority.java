package com.vs.ImageAnnotatorServer.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;


    @Column(name="authority")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserAuthority authority;

    public Authority() {
    }

    public Authority(@NotNull String authority) {
        this.authority = UserAuthority.valueOf(authority);
    }

    public String getAuthority() {
        return authority.name();
    }

    public void setAuthority(String authority) {
        this.authority = UserAuthority.valueOf(authority);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Authority) {
            return this.authority.equals(((Authority) obj).authority);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority.name();
    }
}
