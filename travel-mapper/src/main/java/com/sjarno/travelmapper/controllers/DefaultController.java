package com.sjarno.travelmapper.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DefaultController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @ModelAttribute
    private UserAccount userAccount() {
        return new UserAccount();
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
    @GetMapping("/index") 
    public String index() {
        return "index";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        userAccount.setRoles(roles);
        userAccountRepository.save(userAccount);
        return "redirect:/index";
    }
    
}
