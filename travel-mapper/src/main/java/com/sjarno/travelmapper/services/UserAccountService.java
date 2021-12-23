package com.sjarno.travelmapper.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @Transactional
    public void createUser(UserAccount userAccount) throws IllegalArgumentException {
        Optional<UserAccount> existingAccountByName = userAccountRepository.findByUsername(userAccount.getUsername());
        if (existingAccountByName.isPresent()) {
            throw new IllegalArgumentException("Tämä käyttäjä on jo olemassa");
        }
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        userAccount.setRoles(roles);
        userAccountRepository.save(userAccount);

    }

    public Optional<UserAccount> getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<UserAccount> user = userAccountRepository.findByUsername(
                username);
        return user;
    }

}
