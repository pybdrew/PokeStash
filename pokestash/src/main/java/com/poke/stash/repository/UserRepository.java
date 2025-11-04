package com.poke.stash.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.poke.stash.entity.*;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
    UserEntity findByUserName(String userName);
}
