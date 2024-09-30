package com.project.namma_guest.repository;

import com.project.namma_guest.model.PayingGuestRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayingGuestRoomsRepository extends JpaRepository<PayingGuestRooms,Long> {
    Optional<PayingGuestRooms> findByPayingGuestRoomId(Long payingGuestRoomId);
    Optional<PayingGuestRooms> findByPayingGuestId(Long payingGuestId);

}
