package com.poke.stash.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.stash.entity.BaglistEntity;
import com.poke.stash.entity.ItemEntity;
import com.poke.stash.entity.PokebagEntity;
import com.poke.stash.entity.UserEntity;
import com.poke.stash.repository.BaglistRepository;
import com.poke.stash.repository.ItemRepository;
import com.poke.stash.repository.PokebagRepository;
import com.poke.stash.repository.UserRepository;
import com.poke.stash.service.BagService;


@Controller
public class UserController
{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final PokebagRepository pokebagRepository;
    private final BaglistRepository baglistRepository;

    public UserController(ItemRepository itemRepository, BagService bagService, UserRepository userRepository, PokebagRepository pokebagRepository, BaglistRepository baglistRepository)
    {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.pokebagRepository = pokebagRepository;
        this.baglistRepository = baglistRepository;
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal)
    {
        if (principal == null)
        {
            return "redirect:/login";
        }

        UserEntity user = userRepository.findByUserName(principal.getName());
        if (user == null)
        {
            return "redirect:/login";
        }

        PokebagEntity bag = pokebagRepository.findByUserId(user.getId());

        List<Map<String, Object>> displayItems = new ArrayList<>();
        if (bag != null)
        {
            List<BaglistEntity> bagItems = baglistRepository.findByPokebagId(bag.getBagId());

            for (BaglistEntity bagItem : bagItems)
            {
                ItemEntity item = itemRepository.findById(bagItem.getPokeitemId()).orElse(null);
                if (item != null)
                {
                    Map<String, Object> map = new HashMap<>();
                    map.put("listId", bagItem.getListId());
                    map.put("itemId", item.getItemId());
                    map.put("name", item.getName());
                    map.put("description", item.getDescription());
                    map.put("price", item.getPrice());
                    map.put("quantity", bagItem.getQuantity());
                    displayItems.add(map);
                }
            }
        }
        model.addAttribute("title", "Your Bag");
        model.addAttribute("bagItems", displayItems);
        return "/dashboard";
    }


    @GetMapping("/store")
    public String itemStore(Model model)
    {
        // Fetch all items from the database
        model.addAttribute("title", "Item Store");
        model.addAttribute("items", itemRepository.findAll());
        return "/itemMenu";
    }

    @GetMapping("/addToBag/{itemId}")
    public String addToBagPage(@PathVariable int itemId, Model model, Principal principal)
    {
        ItemEntity item = itemRepository.findById(itemId).orElse(null);
        if (item == null)
        {
            return "redirect:/store";
        }
        model.addAttribute("item", item);
        model.addAttribute("title", "Modify Item");
        return "/pokeItem";
    }
    
    @PostMapping("/updateInventory")
    public String updateInventory(@RequestParam int itemId, @RequestParam int quantity, Principal principal) {
        if (principal == null)
        {
            return "redirect:/login";
        }

        UserEntity user = userRepository.findByUserName(principal.getName());
        if (user == null)
        {
            return "redirect:/login";
        }

        PokebagEntity bag = pokebagRepository.findByUserId(user.getId());
        if (bag == null)
        {
            // create a new bag if it doesn't exist
            bag = new PokebagEntity();
            bag.setUserId(user.getId());
            pokebagRepository.save(bag);
        }

        BaglistEntity bagItem = baglistRepository.findByPokebagIdAndPokeitemId(bag.getBagId(), itemId);

        if (bagItem == null)
        {
            // create new bag item
            bagItem = new BaglistEntity();
            bagItem.setPokebagId(bag.getBagId());
            bagItem.setPokeitemId(itemId);
        }

        // update quantity (even new or existing)
        bagItem.setQuantity(quantity);
        baglistRepository.save(bagItem);

        return "redirect:/dashboard";
    }

    @PostMapping("/removeFromBag")
    public String removeFromBag(@RequestParam("itemId") int itemId, Principal principal)
    {
        if (principal == null)
        {
            return "redirect:/login";
        }

        UserEntity user = userRepository.findByUserName(principal.getName());
        if (user == null)
        {
            return "redirect:/login";
        }

        PokebagEntity bag = pokebagRepository.findByUserId(user.getId());
        if (bag != null)
        {
            BaglistEntity bagItem = baglistRepository.findByPokebagIdAndPokeitemId(bag.getBagId(), itemId);
            if (bagItem != null)
            {
                baglistRepository.delete(bagItem);
            }
        }

        return "redirect:/dashboard";
    }
}
