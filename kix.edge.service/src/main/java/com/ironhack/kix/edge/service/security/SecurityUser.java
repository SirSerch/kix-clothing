package com.ironhack.kix.edge.service.security;

import com.ironhack.kix.edge.service.models.enums.Role;
import com.ironhack.kix.edge.service.models.views.UserView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser extends UserView implements UserDetails {
    private static final long serialVersionUID = -4381938875186527688L;

    static final Logger LOGGER = LoggerFactory.getLogger(SecurityUser.class);

    public SecurityUser(UserView user) {
        super(user);
        LOGGER.info("Loggin: " + this.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        String role = this.getRole().equals(Role.CLIENT) ? "ROLE_CLIENT" : "ROLE_ADMIN";
        System.out.println(this);
        authorities.add( new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
