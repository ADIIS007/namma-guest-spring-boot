package com.project.namma_guest.repository;

import com.project.namma_guest.model.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StayRepositoryTest {
    @Autowired
    private StayRepository stayRepository;

    @BeforeEach
    public void setUp(){
        Stay booking = new Stay();
        booking.setStayId(1L);
        booking.setComment("Booking good");
        booking.setRating(4);
        booking.setRoomId(2L);
        booking.setStartDate(new Date(-2021));
        booking.setEndDate(new Date(2024));
        booking.setPayingGuestId(3L);
        booking.setUserUniqueId(4L);
        stayRepository.save(booking);
    }

    @Test
    public void testfindByStayId(){
        Optional<Stay> stay = stayRepository.findByStayId(1L);
        assertTrue(stay.isPresent());
        assertEquals(stay.get().getStayId(), 1L);
        System.out.println("Stay ID is " + stay.get().getStayId());
    }

    @Test
    public void testFindByRoomId(){
        Optional<Stay> stay = stayRepository.findByRoomId(2L);
        assertTrue(stay.isPresent());
        assertEquals(stay.get().getRoomId(), 2L);
        System.out.println("Room ID is " + stay.get().getStayId());
    }

    @Test
    public void testFindByUserUniqueId(){
        Optional<Stay> stay = stayRepository.findByUserUniqueId(4L);
        assertTrue(stay.isPresent());
        assertEquals(stay.get().getUserUniqueId(), 4L);
        System.out.println("User unique ID is " + stay.get().getUserUniqueId());
    }

    @Test
    public void testFindByRoomIdAndStayId(){
        Optional <Stay> stay = stayRepository.findByRoomIdAndStayId(2L, 1L);
        assertTrue(stay.isPresent());
        assertEquals(stay.get().getRoomId(), 2L);
        assertEquals(stay.get().getStayId(), 1L);
        System.out.println("Room ID is " + stay.get().getRoomId() + " and Stay ID is " + stay.get().getStayId());
    }

    @Test
    public void testFindByPayingGuestId(){
        Optional <Stay> stay = stayRepository.findByPayingGuestId(3L);
        assertTrue(stay.isPresent());
        assertEquals(stay.get().getPayingGuestId(), 3L);
        System.out.println("Paying Guest ID is " + stay.get().getPayingGuestId());
    }

    @Test
    public void testUpdateComment(){
        Optional<Stay> stayOptional = stayRepository.findByStayId(1L);
        assertTrue(stayOptional.isPresent());
        Stay stay = stayOptional.get();
        stay.setComment("New comment");
        stayRepository.save(stay);
        Optional<Stay> updatedStayOptional = stayRepository.findByStayId(1L);
        assertTrue(updatedStayOptional.isPresent());
        Stay updatedStay = updatedStayOptional.get();
        assertEquals("New comment", updatedStay.getComment());
        System.out.println("Comment is " + updatedStay.getComment());
    }

    @Test
    public void testDeleteRating(){
        Optional<Stay> stayOptional = stayRepository.findByStayId(1L);
        assertTrue(stayOptional.isPresent());
        stayRepository.delete(stayOptional.get());
        Optional<Stay> deletedStayOptional = stayRepository.findByStayId(1L);
        assertFalse(deletedStayOptional.isPresent());
    }
}