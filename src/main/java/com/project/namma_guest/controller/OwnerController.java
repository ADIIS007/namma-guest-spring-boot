package com.project.namma_guest.controller;

import com.project.namma_guest.service.PayingGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final PayingGuestService payingGuestService;
    public OwnerController(PayingGuestService payingGuestService) {
        this.payingGuestService = payingGuestService;
    }

    // Test weather the microservice is up or not
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome to Namma Guest this is a test API!");
    }

    // Set up the Hostel while onboarding
    @PostMapping("/hostelCreation")
    public ResponseEntity<String> setupHostelName(String hostelName) {
        String str = "Hostel Name: " + hostelName;
        return ResponseEntity.ok(str);
    }

    // Update the Hostel details after creating
    @PutMapping("/hostelUpdation")
    public ResponseEntity<String> hostelDataUpdation(String hostelData) {
        String str = "Hostel Data: " + hostelData;
        return ResponseEntity.ok(str);
    }

    // Load the hostel details from the database & to make a user look
    @GetMapping("/hostelDetails")
    public ResponseEntity<String> hostelDetails() {
        String str = "Hostel Details: getEmail";
        return ResponseEntity.ok(str);
    }

    // Delete the hostel details from the database
    @DeleteMapping("/hostelDeletion")
    public ResponseEntity<String> hostelDeletion() {
        String str = "Hostel Details: deleteEmail";
        return ResponseEntity.ok(str);
    }

    // User Data this helps user to edit their profile data
    @GetMapping("/userData")
    public ResponseEntity<String> userData() {
        String str = "User Data: get the Email from JWT Token";
        return ResponseEntity.ok(str);
    }
}