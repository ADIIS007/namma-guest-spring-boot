package com.project.namma_guest.controller;

import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.service.PayingGuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @PostMapping("/list-pgs")
    public ResponseEntity<List<PayingGuest>> listPGsByDistance(double longitude, double latitude, double distance) {
        //TODO: Implement this method & return the list of PGs within the given distance
        List<PayingGuest>temp=new ArrayList<PayingGuest>();
        return ResponseEntity.ok(temp);
        //TODO: Implement this method & return the list of PGs within the given distance
    }
    @PostMapping("/about-pg")
    public ResponseEntity<PayingGuest> aboutPg(double longitude, double latitude, double distance) {
        //TODO: Implement this method & return the details about a PG
        PayingGuest temp=new PayingGuest();
        return ResponseEntity.ok(temp);
    }
    @PostMapping("/my-profile")
    public ResponseEntity<String> myProfile() {
        //TODO: Implement this method & return the details about the user
        return ResponseEntity.ok("My Profile");
    }
    @PostMapping("/pg-location")
    public ResponseEntity<String> pgLocation() {
        //TODO: Implement this method & return the locations of the PG for map
        return ResponseEntity.ok("PG Location");
    }
    @PostMapping("/pg-booking")
    public ResponseEntity<String> pgBooking() {
        //TODO: Implement this method & return the booking details
        return ResponseEntity.ok("PG Booking");
    }
}