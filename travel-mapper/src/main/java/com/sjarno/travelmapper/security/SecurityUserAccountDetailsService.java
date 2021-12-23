package com.sjarno.travelmapper.security;

import java.util.Optional;
import java.util.stream.Collectors;

import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserAccountDetailsService implements UserDetailsService{

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(username);
        if (userAccount.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                userAccount.get().getUsername(),
                userAccount.get().getPassword(),
                true,
                true,
                true, 
                true,
                userAccount.get().getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
            );
        }
        throw new UsernameNotFoundException("No such user: "+username);
    }
    
}
