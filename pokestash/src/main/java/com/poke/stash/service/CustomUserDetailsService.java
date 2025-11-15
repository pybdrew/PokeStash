package com.poke.stash.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poke.stash.entity.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserService userService;

    public CustomUserDetailsService(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
       UserEntity user = userService.findByUserName(username);
       if(user == null)
       {
        throw new UsernameNotFoundException("User not found");
       }

       return User.builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
    
}
