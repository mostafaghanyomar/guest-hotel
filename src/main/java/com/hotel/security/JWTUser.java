package com.hotel.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
public class JWTUser implements UserDetails {

    private static final long serialVersionUID = -3677247933953168385L;

    private final Long id;
    private final Date lastModified;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Boolean enabled;

    public JWTUser(
          Long id,
          Date lastModified,
          String username,
          String firstname,
          String lastname,
          String email,
          String password,
          Boolean enabled,
          Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.lastModified = lastModified;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
