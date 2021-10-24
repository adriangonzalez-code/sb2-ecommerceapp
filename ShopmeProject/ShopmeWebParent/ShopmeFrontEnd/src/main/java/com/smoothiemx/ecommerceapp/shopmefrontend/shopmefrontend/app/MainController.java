package com.smoothiemx.ecommerceapp.shopmefrontend.shopmefrontend.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
}