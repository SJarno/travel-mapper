package com.sjarno.travelmapper.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.sjarno.travelmapper.models.UserAccount;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
public class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    private UserAccount minaIte;
    private UserAccount sinaIte;

    @BeforeEach
    public void setUp() {
        minaIte = new UserAccount();
        minaIte.setUsername("Minä ite");
        minaIte.setPassword("salainensana");

        sinaIte = new UserAccount();
        sinaIte.setUsername("Sinä ite");
        sinaIte.setPassword("54321");

        userAccountRepository.save(minaIte);
        userAccountRepository.save(sinaIte);
    }

    @Test
    void testFindByUsername() {
        assertEquals(2, userAccountRepository.findAll().size());
        assertEquals(minaIte, userAccountRepository.findByUsername("Minä ite").get());
        assertNotEquals(sinaIte, userAccountRepository.findByUsername("Minä ite").get());
    }
}
