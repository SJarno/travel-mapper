package com.sjarno.travelmapper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/front-page")
    public String getFrontFragment() {
        return "fragments/section :: front-page";
    }

    @GetMapping("/map-page")
    public String getMapFragment() {
        return "fragments/section :: map-page";
    }

}
