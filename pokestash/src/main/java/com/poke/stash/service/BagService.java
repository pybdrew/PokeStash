package com.poke.stash.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poke.stash.entity.BaglistEntity;
import com.poke.stash.entity.PokebagEntity;
import com.poke.stash.entity.UserEntity;
import com.poke.stash.repository.BaglistRepository;
import com.poke.stash.repository.PokebagRepository;
import com.poke.stash.repository.UserRepository;

@Service
public class BagService
{

    private final UserRepository userRepository;
    private final PokebagRepository pokebagRepository;
    private final BaglistRepository baglistRepository;

    public BagService(UserRepository userRepository, PokebagRepository pokebagRepository,BaglistRepository baglistRepository)
    {
        this.userRepository = userRepository;
        this.pokebagRepository = pokebagRepository;
        this.baglistRepository = baglistRepository;
    }

    @Transactional
    public String addItemToUserBag(String username, int itemId)
    {
        UserEntity user = userRepository.findByUserName(username);
        if (user == null)
        {
            return "User not found!";
        }

        PokebagEntity bag = pokebagRepository.findByUserId(user.getId());
        if (bag == null)
        {
            bag = new PokebagEntity();
            bag.setUserId(user.getId());
            pokebagRepository.save(bag);
        }

        BaglistEntity bagItem = baglistRepository.findByPokebagIdAndPokeitemId(bag.getBagId(), itemId);
        if (bagItem != null)
        {
            // Item exists, increment quantity
            bagItem.setQuantity(bagItem.getQuantity() + 1);
        } 
        else
        {
            // New item
            bagItem = new BaglistEntity();
            bagItem.setPokebagId(bag.getBagId());
            bagItem.setPokeitemId(itemId);
            bagItem.setQuantity(1);
        }
        baglistRepository.save(bagItem);

        return "Item added to your bag!";
    }

    @Transactional
    public String updateBagItemQuantity(int bagListId, int quantity)
    {
        BaglistEntity bagItem = baglistRepository.findById(bagListId).orElse(null);
        if (bagItem == null)
        {
            return "Bag item not found!";
        }

        bagItem.setQuantity(quantity);
        baglistRepository.save(bagItem);
        return "Quantity updated!";
    }
}