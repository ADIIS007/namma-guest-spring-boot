package com.project.namma_guest.controller;

import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.service.PayingGuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final PayingGuestService payingGuestService;

    public HomeController(PayingGuestService payingGuestService) {
        this.payingGuestService = payingGuestService;
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome to Namma Guest this is a test API!");
    }
    @GetMapping("/list-guests")
    public ResponseEntity<List<PayingGuest>> listGuests() {
        return ResponseEntity.ok(payingGuestService.getAllPayingGuest());
    }
}
