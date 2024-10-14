package com.project.namma_guest.repository;

import com.project.namma_guest.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    public void setUp() {
        Users user1 = new Users();
        user1.setUserUniqueId(1L);
        user1.setGiven_name("John");
        user1.setFamily_name("Doe");
        user1.setEmail("xyz@test.com");
        user1.setContactNumber("+1234567890");
        user1.setCurrentLocation(null);
        user1.setOTP("123456");
        user1.setOTPGeneratedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        user1.setIsVerified(false);

        usersRepository.save(user1);
    }
    @Test
    public void testfindUser() {
        Optional<Users> optionalUser = usersRepository.findByUserUniqueId(1L);
        assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get().getUserUniqueId());
    }
    @Test
    public void testCheckUserVerificationStatus() {

        Optional<Users> optionalUser = usersRepository.findByUserUniqueId(1L);
        assertTrue(optionalUser.isPresent());
        Users user = optionalUser.get();
        assertFalse(user.getIsVerified());

    }
    @Test
    public void updateOTP(){
        Optional<Users> optionalUser = usersRepository.findByUserUniqueId(1L);
        assertTrue(optionalUser.isPresent());
        Users user = optionalUser.get();
        user.setOTP("123");
        usersRepository.save(user);

        Optional<Users> optionalUser2 = usersRepository.findByUserUniqueId(1L);
        assertTrue(optionalUser2.isPresent());
        Users user2 = optionalUser2.get();
        assertEquals("123", user2.getOTP());
    }
}
