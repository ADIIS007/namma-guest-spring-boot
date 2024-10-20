package com.project.namma_guest.controller;

import com.project.namma_guest.DTO.Request.Email;
import com.project.namma_guest.DTO.Request.VerifyOTP;
import com.project.namma_guest.helper.AuthenticationHelper;
import com.project.namma_guest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationHelper authenticationHelper;
    UserService userService;
    AuthenticationController(AuthenticationHelper authenticationHelper, UserService userService) {
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
    }

    //This function is to check weather the microservice is working or not
    @GetMapping("/auth-health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK Auth Telling You");
    }

    //Send OTP to the user to login
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Email email) {
        log.info("Sending OTP to {} endpoint", email);
        try {
            return userService.sendOtp(email.getEmail());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.TOO_EARLY).body("Wait for sometime before trying to login");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some problem occurred while sending OTP");
        }
    }

    //Verify the OTP sent to a specific Email
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOTP verifyOTP) {
        log.info("Verifying OTP for {} endpoint", verifyOTP);
        try {
            return userService.verifyOtp(verifyOTP.getEmail(), verifyOTP.getOtp());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It looks like OTP is wrong");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It looks like email address is invalid");
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("OTP Expired");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some problem occurred while verifying OTP");
        }
    }

    // This should re-generate the email and
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Email email) {
        log.info("resending OTP to {}", email);
        try {
            return userService.resendOtp(email.getEmail());
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Wait before requesting for resending OTP");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
        } catch (Exception  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some problem occurred while processing the request");
        }
    }
}
