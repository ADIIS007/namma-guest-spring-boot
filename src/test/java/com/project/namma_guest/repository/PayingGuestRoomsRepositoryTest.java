package com.project.namma_guest.repository;
import com.project.namma_guest.model.PayingGuestRooms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PayingGuestRoomsRepositoryTest {

    @Autowired
    private PayingGuestRoomsRepository payingGuestRoomsRepository;

    @BeforeEach
    public void setUp() {
        PayingGuestRooms pgRooms = new PayingGuestRooms();
        pgRooms.setPayingGuestRoomId(1L);
        pgRooms.setPayingGuestId(1L);
        pgRooms.setRoomNumber("101");
        pgRooms.setRoomType(null);
        pgRooms.setAvailable(true);
        pgRooms.setAvailableFrom(new Date());
        pgRooms.setRent(500.00); //
        pgRooms.setSecurityDeposit(1000.00);
        pgRooms.setMaintenance(50.00);
        pgRooms.setVerified(true);

        payingGuestRoomsRepository.save(pgRooms);
    }

    @Test
    public void testFindByPayingGuestRoomId() {
        Optional<PayingGuestRooms> pgRoomOptional = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(pgRoomOptional.isPresent(), "Guest room should be present");
    }

    @Test
    public void testFindByGuestId(){
        Optional<PayingGuestRooms> pgRooms = payingGuestRoomsRepository.findByPayingGuestId(1L);
        assertTrue(pgRooms.isPresent());
    }

    @Test
    public void updateRoomNumber(){
        Optional<PayingGuestRooms> pgRooms = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(pgRooms.isPresent(),"Guest room should be present");
        PayingGuestRooms pgRoom = pgRooms.get();
        pgRoom.setRoomNumber("111");
        payingGuestRoomsRepository.save(pgRoom);

        Optional<PayingGuestRooms> checkRoom = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(checkRoom.isPresent(),"Guest room should be present");
        assertEquals("111",checkRoom.get().getRoomNumber());
    }

    @Test
    public void updateRent(){
        Optional<PayingGuestRooms> pgRooms = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(pgRooms.isPresent(),"Guest room should be present");
        PayingGuestRooms pgRoom = pgRooms.get();
        pgRoom.setRent(1500.00);
        payingGuestRoomsRepository.save(pgRoom);

        Optional <PayingGuestRooms> checkRoom = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(checkRoom.isPresent(),"Guest room should be present");
        assertEquals(1500,checkRoom.get().getRent());
    }
    @Test
    public void deleteMaintenece(){
        Optional <PayingGuestRooms> payingGuestRooms = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertTrue(payingGuestRooms.isPresent());
        payingGuestRoomsRepository.delete(payingGuestRooms.get());

        Optional<PayingGuestRooms> checkRooms = payingGuestRoomsRepository.findByPayingGuestRoomId(1L);
        assertFalse(checkRooms.isPresent(),"Guest room should be present");
    }
}