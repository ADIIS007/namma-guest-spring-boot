package com.project.namma_guest.service;

import com.project.namma_guest.helper.Utilities;
import com.project.namma_guest.model.Users;
import com.project.namma_guest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PayingGuestServiceTest {
    @Autowired
    private UsersRepository usersRepository;
    @Test
    public void validEmail() {
        assertTrue(Utilities.isValidEmail("test@gmail.com"));
        assertFalse(Utilities.isValidEmail("test@.com"));
        assertFalse(Utilities.isValidEmail("testgmail.com"));
        assertFalse(Utilities.isValidEmail("test@gmail"));
    }
    @Test
    public void ownerExists() {
        Users user = new Users();
        user.setGiven_name("John");
        user.setFamily_name("Doe");
        user.setEmail("test@example.com");
        user.setContactNumber("1234567890");
        user.setUserType("tenant");
        user.setIsVerified(true);
        user.setCurrentLocation(null);
        user.setCurrentPayingGuest(null);
        user.setOwnsPayingGuest(null);

        user.setOTP("123456");
        user.setOTPGeneratedAt(new Date());
        usersRepository.save(user);

        assertNotNull(usersRepository.findUsersByEmail("test@example.com"));
        System.out.println(usersRepository.findUsersByEmail("test@example.com"));
        assertNull(usersRepository.findUsersByEmail("test@gmail.com"));
        System.out.println(usersRepository.findUsersByEmail("test@gmail.com")); // prints null
    }
}