package com.project.namma_guest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.namma_guest.service.PayingGuestService;

@WebMvcTest(HomeController.class)
public class HomeControllerTests {
    
    @MockBean
    private PayingGuestService payingGuestService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testApi() {
        ResponseEntity<String> response = homeController.test();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Welcome to Namma Guest this is a test API!", response.getBody());
    }
}
