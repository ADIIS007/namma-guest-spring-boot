package com.project.namma_guest.repository;

import com.project.namma_guest.NammaGuestApplication;
import com.project.namma_guest.model.PayingGuest;
import com.project.namma_guest.model.PayingGuestRooms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ContextConfiguration;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ContextConfiguration(classes = NammaGuestApplication.class)
class PayingGuestRepositoryTest {

    @Autowired
    private PayingGuestRepository payingGuestRepository;

    @BeforeEach
    public void saveTeacher(){
        PayingGuest sampleGuest = new PayingGuest();
        sampleGuest.setPayingGuestId(1L);
        sampleGuest.setName("John Doe");
        sampleGuest.setAddress("123 Main Street");
        sampleGuest.setCity("New York");
        sampleGuest.setState("NY");
        sampleGuest.setCountry("USA");
        sampleGuest.setEmail("john.doe@example.com");
        sampleGuest.setContactNumber("1234567890");
        sampleGuest.setLocation(null);
        sampleGuest.setVerified(true);
        sampleGuest.setGuestType("Long Term");
        payingGuestRepository.save(sampleGuest);
    }
    @Test
    public void testFindById() {
        Optional<PayingGuest> foundGuest = payingGuestRepository.findById(1L);
        assertTrue(foundGuest.isPresent());
        assertEquals("John Doe", foundGuest.get().getName());
    }
    @Test
    public void testUpdateGuest() {
        Optional<PayingGuest> guestOptional = payingGuestRepository.findById(1L);
        assertTrue(guestOptional.isPresent());
        PayingGuest guest = guestOptional.get();
        guest.setCity("Los Angeles");
        payingGuestRepository.save(guest);
        PayingGuest updatedGuest = payingGuestRepository.findById(1L).get();
        assertEquals("Los Angeles", updatedGuest.getCity());
    }
    @Test
    public void testDeleteGuest() {
        Optional<PayingGuest> guestOptional = payingGuestRepository.findById(1L);
        assertTrue(guestOptional.isPresent());
        payingGuestRepository.delete(guestOptional.get());
        Optional<PayingGuest> deletedGuest = payingGuestRepository.findById(1L);
        assertFalse(deletedGuest.isPresent());
    }
    @Test
    public void testVerified() {
        Optional<PayingGuest> guestOptional = payingGuestRepository.findById(1L);
        assertTrue(guestOptional.isPresent());
        assertTrue(guestOptional.get().isVerified());
    }
    @Test
    public void testPrintAll(){
        Optional<PayingGuest> guestOptional = payingGuestRepository.findById(1L);
        assertTrue(guestOptional.isPresent());
        PayingGuest guest = guestOptional.get();
        System.out.println(guest.toString());
    }
}