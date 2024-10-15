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

}
