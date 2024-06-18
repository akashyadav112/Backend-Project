package com.Ecom.AuthService.Security.Oauth2;

import com.Ecom.AuthService.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * This class will be used by spring security fwk to enable the Oath based authentication
 */
public class CustomUserDetails implements UserDetails {

    /** the user class implemented by us all the details of user is present here**/
    private final User user;
    public CustomUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailId();
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
