package com.zhima.webauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by superz on 2018/8/1.
 */
public class JwtUser implements UserDetails{

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //@JSONField(serialize = false)
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //@JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //@JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //@JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //@JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
