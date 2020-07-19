package com.quiz.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {

    public User(long userId, String username, String password, boolean accountExpired, boolean accountLocked, boolean credentialNotexpired, boolean enabled) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialNotexpired = credentialNotexpired;
        this.enabled = enabled;
    }

    public User(long userId,String username){
        this.userId = userId;
        this.username = username;
    }

    @Getter
    private long userId;
    private String username;
    private String password = null;
    private boolean accountExpired = false;
    private boolean accountLocked = false;
    private boolean credentialNotexpired = false;
    private boolean enabled = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
