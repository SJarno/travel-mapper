package com.sjarno.travelmapper.repositories;

import java.util.Optional;

import com.sjarno.travelmapper.models.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);

}
