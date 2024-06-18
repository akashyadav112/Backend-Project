package com.Ecom.AuthService.Security.Oauth2;

import com.Ecom.AuthService.Models.User;
import com.Ecom.AuthService.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomSpringUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailId(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User is not present with given email id");
        }
        User savedUser = user.get();
        return new CustomUserDetails(savedUser);
    }
}
