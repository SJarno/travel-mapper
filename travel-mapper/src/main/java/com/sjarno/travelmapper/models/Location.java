package com.sjarno.travelmapper.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
@Data
public class Location extends AbstractPersistable<Long> {

    @NotBlank(message = "Sijainti ei saa olla tyhjä")
    @Column(name = "location_name")
    private String name;

    
    private double latitude;
    private double longitude;

    @JsonIgnore
    @ManyToOne
    private UserAccount userAccount;
}
