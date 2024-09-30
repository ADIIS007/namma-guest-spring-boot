package com.project.namma_guest.repository;

import com.project.namma_guest.model.RoomsPicture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomsPictureRepository extends JpaRepository<RoomsPicture, Long> {

    Optional<RoomsPicture> findByPayingGuestRoomId(Long payingGuestRoomId);
    Optional<RoomsPicture> findBypictureId(Long pictureId);
}
