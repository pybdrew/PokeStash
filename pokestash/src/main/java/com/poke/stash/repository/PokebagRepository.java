package com.poke.stash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poke.stash.entity.PokebagEntity;

@Repository
public interface PokebagRepository extends JpaRepository<PokebagEntity, Integer>
{
    
}
