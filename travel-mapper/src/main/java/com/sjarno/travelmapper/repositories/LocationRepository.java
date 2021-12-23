package com.sjarno.travelmapper.repositories;

import com.sjarno.travelmapper.models.Location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long>{
    
}
