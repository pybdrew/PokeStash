package com.poke.stash.service;
import com.poke.stash.entity.UserEntity;
import com.poke.stash.repository.*;

// Calls repository method AND Holds business logic
public class UserService
{
    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserEntity findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }
}
