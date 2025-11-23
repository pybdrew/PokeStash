package com.poke.stash.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poke.stash.entity.UserEntity;
import com.poke.stash.repository.*;

@Service
// Calls repository method AND Holds business logic
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }

    public void registerUser(UserEntity user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean existsByUserName(String userName) 
    {
        return userRepository.existsByUserName(userName);
    }
}
