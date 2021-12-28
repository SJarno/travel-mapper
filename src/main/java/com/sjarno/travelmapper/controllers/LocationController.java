package com.sjarno.travelmapper.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sjarno.travelmapper.models.Location;
import com.sjarno.travelmapper.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ResponseBody
    @RequestMapping(value = "/locations/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addNewLocation(
            @RequestParam String locationName,
            @RequestParam double latitude,
            @RequestParam double longitude,
            RedirectAttributes redirectAttributes) {

        System.out.println();
        System.out.println("Tultiin oikeaan paikkaan!");
        System.out.println();

        try {
            Location location = locationService.saveLocation(
                    locationName,
                    latitude,
                    longitude);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            
            Map<String, String> response = new HashMap<>();
            response.put("errorMessage", e.getMessage());
        
            /* return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response); */
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header("error", e.getMessage()).build();
            

        }

    }

}
