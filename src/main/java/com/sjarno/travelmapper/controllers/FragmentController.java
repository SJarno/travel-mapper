package com.sjarno.travelmapper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/info-page")
    public String getInfoPage() {
        return "fragments/section :: info-page";
    }

    @GetMapping("/register-page")
    public String getFrontFragment() {
        return "fragments/section :: register-page";
    }

    @GetMapping("/map-page")
    public String getMapFragment() {
        return "fragments/section :: map-page";
    }

}
