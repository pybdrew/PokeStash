package com.poke.stash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.stash.dto.UserDTO;
import com.poke.stash.entity.UserEntity;
import com.poke.stash.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class AuthController
{

    private final UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String homePage(Model model)
    {
        model.addAttribute("title", "Home");
        return "home";
    }
    
    @GetMapping("/login")
    public String loginPage(Model model)
    {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "register";
        }

        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword()))
        {
            model.addAttribute("passwordError", "Passwords do not match");
            return "register";
        }

        // Map DTO to Entity
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword());

        // save user
        userService.registerUser(userEntity);
        return "redirect:/";
    }
    

    
}
