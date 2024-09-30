package com.project.namma_guest.repository;

import com.project.namma_guest.model.RoomsPicture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomsPictureRepositoryTest {

    @Autowired
    private RoomsPictureRepository roomsPictureRepository ;

    @BeforeEach
    public void setUp(){
        RoomsPicture room1 = new RoomsPicture();
        room1.setPictureId(1L);
        room1.setPayingGuestRoomId(1L);
        room1.setPictureDescription("Description");
        room1.setPicturePath("src/main/path");
        roomsPictureRepository.save(room1);
    }

    @Test
    public void testfindByPayingGuestRoomId(){
        Optional<RoomsPicture> room1 = roomsPictureRepository.findByPayingGuestRoomId(1L);
        assertTrue(room1.isPresent());
        assertEquals(1L, room1.get().getPayingGuestRoomId());
    }
    @Test
    public void testfindByPictureId(){
        Optional<RoomsPicture> room1 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room1.isPresent());
        assertEquals(1L, room1.get().getPictureId());
    }

    @Test
    public void testUpdatePictureDescription(){
        Optional<RoomsPicture> room1 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room1.isPresent());

        room1.get().setPictureDescription("New Description");
        roomsPictureRepository.save(room1.get());

        Optional<RoomsPicture> room2 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room2.isPresent());
        assertEquals("New Description", room2.get().getPictureDescription());

        System.out.println(room2.get().getPictureDescription());

    }

    @Test
    public void testUpdatePicturePath(){
        Optional<RoomsPicture> room1 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room1.isPresent());
        room1.get().setPicturePath("New Path");
        roomsPictureRepository.save(room1.get());
        Optional<RoomsPicture> room2 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room2.isPresent());
        assertEquals("New Path", room2.get().getPicturePath());

        System.out.println("Path=> "+ room2.get().getPicturePath());
    }
    @Test
    public void testDelete(){
        Optional<RoomsPicture> room1 = roomsPictureRepository.findBypictureId(1L);
        assertTrue(room1.isPresent());
        roomsPictureRepository.delete(room1.get());

        Optional<RoomsPicture> room2 = roomsPictureRepository.findBypictureId(1L);
        assertFalse(room2.isPresent());

    }
}