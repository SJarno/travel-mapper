package com.sjarno.travelmapper.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.services.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DefaultController {

    @Autowired
    private UserAccountService userAccountService;

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
    public String registerUser(
            @Valid @ModelAttribute UserAccount userAccount,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            model.addAttribute("error", errors);
            return "index";
        }

        try {
            userAccountService.createUser(userAccount);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
        redirectAttributes.addFlashAttribute("success", "Käyttäjä luotu! Kirjaudu sisään palveluun.");
        return "redirect:/index";
    }

}
