package com.heapix.events.config.security;

import com.heapix.events.persistence.model.User;
import com.heapix.events.persistence.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

import static java.util.Collections.singleton;

public class UserAuth implements UserDetails {

    private User user;

    public UserAuth( @NotNull User user) {
        this.user = user;
    }


    public Long getId() {return user.getId();}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singleton(new SimpleGrantedAuthority(UserRole.getById(user.getRole()).getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return true;
    }
}
