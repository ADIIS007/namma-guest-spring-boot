package com.project.namma_guest.controller;

import com.project.namma_guest.DTO.Request.Email;
import com.project.namma_guest.DTO.Request.VerifyOTP;
import com.project.namma_guest.helper.AuthenticationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationHelper authenticationHelper;
    AuthenticationController(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
    }

    //This function is to check weather the microservice is working or not
    @GetMapping("/auth-health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK Auth Telling You");
    }

    //Send OTP to the user to login
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Email email) {
        log.info("Sending OTP to {}", email);
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was not sent at lest 2 min ago - 429 Too Many Requests
        // Step 4 - SEND OTP & time before it cant resend - 200 OK
        return authenticationHelper.sendOtp(email.getEmail());
    }

    //Verify the OTP sent to a specific Email
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOTP verifyOTP) {
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was sent at lest 2 min ago - 410 Gone/400 Bad Request
        // Step 4 - SEND OTP & time before it cant resend - 200 OK
        return authenticationHelper.verifyOtp(verifyOTP.getEmail(), verifyOTP.getOtp());
    }

    // This should re-generate the email and
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Email email) {
        log.info("resending OTP to {}", email);
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was not sent at lest 2 min ago - 429 Too Many Requests
        // Step 4 - SEND OTP & time before it cant resend again - 200 OK
        return authenticationHelper.sendOtp(email.getEmail());
    }
}
