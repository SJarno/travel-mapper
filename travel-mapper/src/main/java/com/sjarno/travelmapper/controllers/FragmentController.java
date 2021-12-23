package com.sjarno.travelmapper.controllers;

import java.util.Optional;

import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @Autowired
    private UserAccountService userAccountService;

    
    @GetMapping("/front-page")
    public String getFrontFragment() {
        return "fragments/section :: front-page";
    }
    @GetMapping("/map-page")
    public String getMapFragment() {
        return "fragments/section :: map-page";
    }
    @GetMapping("/user-page")
    public String getUserFragment(Model model) {
        Optional<UserAccount> authUser = userAccountService.getAuthenticatedUser();
        if (authUser.isPresent()) {
            model.addAttribute("user", authUser.get());
        }
        return "fragments/section :: user-page";
    }
    
}
