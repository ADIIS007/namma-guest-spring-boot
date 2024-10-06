package com.project.namma_guest.controller;

import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.service.PayingGuestService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    private final PayingGuestService payingGuestService;
    public HomeController(PayingGuestService payingGuestService) {
        this.payingGuestService = payingGuestService;
    }

    // Test weather the microservice is up or not
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome to Namma Guest this is a test API!");
    }

    // Get list of PGs within a given distance from the given coordinates
    @PostMapping("/list-pgs-within-distance")
    public ResponseEntity<List<PayingGuest>> listPGsByDistance(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam(defaultValue = "5.0") double distance) {
        //TODO: Implement this method & return the list of PGs within the given distance
        // Step 1 - Check the latitude and longitude
        // Step 2 - Get the list of PGs within the given distance
        // Step 3 - Return the list
        List<PayingGuest>temp=new ArrayList<PayingGuest>();
        return ResponseEntity.ok(temp);
    }

    // Get details of a given paying guest
    @GetMapping("/about-pg/{id}")
    public ResponseEntity<PayingGuest> aboutPg(@PathVariable String id) {
        //TODO: Implement this method & return the details about a PG
        // Step 1 - Check the payingGuestId if not exist - 404 NOT FOUND
        // Step 2 - Get the details of the PG weather visibility is public or not - 403 FORBIDDEN
        // Step 3 - Return the details
        PayingGuest temp=new PayingGuest();
        return ResponseEntity.ok(temp);
    }

    //load all the data regarding the user
    @GetMapping("/my-profile/{id}")
    public ResponseEntity<String> myProfile(@PathVariable String id) {
        //TODO: Implement this method & return the details about the user
        // Step 1 - Get the user details using Email (later from the JWT token) - 404 Not Found
        // Step 2 - If user not found - return 404 Not Found status code
        // Step 3 - Return the details - 200 OK
        return ResponseEntity.ok("My Profile");
    }

    //Modify the User with respect to the user request
    @PutMapping("/my-profile")
    public ResponseEntity<String> updateMyProfile(){
        //TODO: Implement this method & return the details about the user
        // Step 1 - Get the user details using Email (later from the JWT token) - 404 Not Found
        // Step 2 - Validate the request (input validation, data format etc.) - 400 Bad Request
        // Step 3 - Update the user details (only update able fields) - 403 Forbidden
        // Step 4 - Return the details of the user if updated - 200 OK
        return ResponseEntity.ok("My Profile Updated");
    }
}