package com.marketplace.marketplace.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtAuthUser implements UserDetails {

    private static final long serialVersionUID = 3903713637310373397L;

    private final String username;
    private final String email;
    private final String password;
    private final boolean isActive;
    private final Date lastUpdate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtAuthUser(
            String username,
            String email,
            String password,
            boolean isActive,
            Date lastUpdate,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.lastUpdate = lastUpdate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
}
