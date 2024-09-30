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
    @GetMapping("/auth-health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK Auth Telling You");
    }
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Email email) {
        log.info("Sending OTP to " + email);
        return authenticationHelper.sendOtp(email.getEmail());
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOTP verifyOTP) {
        return authenticationHelper.verifyOtp(verifyOTP.getEmail(), verifyOTP.getOtp());
    }
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Email email) {
        log.info("Sending OTP to " + email);
        return authenticationHelper.sendOtp(email.getEmail());
    }
}
