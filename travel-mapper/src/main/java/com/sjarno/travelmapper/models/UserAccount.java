package com.sjarno.travelmapper.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_accounts", 
    uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
public class UserAccount extends AbstractPersistable<Long> {

    @NotBlank(message = "Käyttäjätunnus ei saa olla tyhjä")
    private String username;
    @NotBlank(message = "Salasana ei saa olla tyhjä")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
    
}
