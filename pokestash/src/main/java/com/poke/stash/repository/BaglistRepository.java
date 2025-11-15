package com.poke.stash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poke.stash.entity.BaglistEntity;

@Repository
public interface BaglistRepository extends JpaRepository<BaglistEntity, Integer>
{
    
}
