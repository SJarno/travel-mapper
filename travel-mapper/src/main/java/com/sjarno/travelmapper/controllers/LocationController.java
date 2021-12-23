package com.sjarno.travelmapper.controllers;

import java.util.List;

import com.sjarno.travelmapper.models.Location;
import com.sjarno.travelmapper.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @ResponseBody
    @GetMapping("/locations/all")
    public List<Location> getAllLocations() {
        /* Response entity here */
        return locationService.getAllLocations();
    }

    //@ResponseBody
    @PostMapping("/locations/add")
    public String addNewLocation(
        @RequestParam String locationName,
        @RequestParam double latitude,
        @RequestParam double longitude) {
        
        Location location = locationService.saveLocation(
            locationName,
            latitude,
            longitude);
        return "redirect:/index";
    }
    
}
