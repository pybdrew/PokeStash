package com.poke.stash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poke.stash.entity.BaglistEntity;

@Repository
public interface BaglistRepository extends JpaRepository<BaglistEntity, Integer>
{
    List<BaglistEntity> findByPokebagId(int pokebagId);
    BaglistEntity findByPokebagIdAndPokeitemId(int pokebagId, int pokeitemId);
}
