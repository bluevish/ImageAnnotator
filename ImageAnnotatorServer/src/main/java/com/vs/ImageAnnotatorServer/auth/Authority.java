package com.vs.ImageAnnotatorServer.auth;

import com.vs.ImageAnnotatorServer.utils.helper.EnumeratedValue;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// TODO : Check if authorities can be validated at the same time as User while persisting in DB
@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name="authority")
    @NotNull
    @EnumeratedValue(enumClass = UserAuthority.class)
    private String authority;

    public Authority() {

    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
        return this.authority;
    }
}
