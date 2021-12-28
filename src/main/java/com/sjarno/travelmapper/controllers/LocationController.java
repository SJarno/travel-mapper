package com.sjarno.travelmapper.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sjarno.travelmapper.models.Location;
import com.sjarno.travelmapper.services.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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
            @RequestParam double longitude) throws NumberFormatException, MethodArgumentTypeMismatchException {

        System.out.println();
        System.out.println("Tultiin oikeaan paikkaan!");
        System.out.println();


        try {
            locationService.saveLocation(
                    locationName,
                    latitude,
                    longitude);
            
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            /*
             * Unprocessable entity on wrong user input:
             * https://stackoverflow.com/questions/7939137/right-http-status-code-to-wrong-
             * input
             */
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).header("error", e.getMessage()).build();

        }

    }

}
