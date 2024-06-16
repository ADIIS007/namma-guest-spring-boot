package com.project.namma_guest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/test")
    public String test() {
        return "Welcome to Namma Guest this is a test API!";
    }
}
