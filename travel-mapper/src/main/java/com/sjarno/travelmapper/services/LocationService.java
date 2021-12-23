package com.sjarno.travelmapper.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.sjarno.travelmapper.models.Location;
import com.sjarno.travelmapper.models.UserAccount;
import com.sjarno.travelmapper.repositories.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Transactional
    public Location saveLocation(String name, double latitude, double longitude) throws IllegalArgumentException{
        if (name.isBlank()) {
            throw new IllegalArgumentException("Paikan nimi ei saa olla tyhjä");
        }
        Optional<UserAccount> userAccount = userAccountService.getAuthenticatedUser();
        if (userAccount.isPresent()) {
            Location location = new Location();
            location.setName(name);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            location.setUserAccount(userAccount.get());
            List<Location> userLocations = userAccount.get().getLocations();
            userLocations.add(location);
            userAccount.get().setLocations(userLocations);
            locationRepository.save(location);
            return location;
        }
        throw new UsernameNotFoundException("Käyttäjää ei löytynyt");

    }

    public List<Location> getAllLocations() {
        List<Location> locations = userAccountService.getAuthenticatedUser().get().getLocations();
        return locations;
    }
}
