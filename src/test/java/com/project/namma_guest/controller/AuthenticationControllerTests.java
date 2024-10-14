package com.project.namma_guest.controller;

import com.project.namma_guest.DTO.Request.Email;
import com.project.namma_guest.DTO.Request.VerifyOTP;
import com.project.namma_guest.helper.AuthenticationHelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTests {

    @MockBean
    private AuthenticationHelper authenticationHelper;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
         MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHealth() {
        ResponseEntity<String> response = authenticationController.health();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("OK Auth Telling You", response.getBody());
    }

    @Test
    public void testSendOtp() {
        Email email = new Email();
        email.setEmail("test@example.com");

        Mockito.when(authenticationHelper.sendOtp(email.getEmail())).thenReturn(ResponseEntity.ok("OTP Sent"));

        ResponseEntity<String> response = authenticationController.sendOtp(email);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("OTP Sent", response.getBody());
    }

    @Test
    public void testVerifyOtp() {
        VerifyOTP verifyOtp = new VerifyOTP();
        verifyOtp.setEmail("test@example.com");
        verifyOtp.setOtp("123456");

        Mockito.when(authenticationHelper.verifyOtp(verifyOtp.getEmail(), verifyOtp.getOtp()))
                .thenReturn(ResponseEntity.ok("OTP Verified"));

        ResponseEntity<String> response = authenticationController.verifyOtp(verifyOtp);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("OTP Verified", response.getBody());
    }

    @Test
    public void testResendOtp() {
        Email email = new Email();
        email.setEmail("test@example.com");

        Mockito.when(authenticationHelper.sendOtp(email.getEmail())).thenReturn(ResponseEntity.ok("OTP Resent"));

        ResponseEntity<String> response = authenticationController.resendOtp(email);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("OTP Resent", response.getBody());
    }
}
