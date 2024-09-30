package com.project.namma_guest.repository;

import com.project.namma_guest.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StayRepository extends JpaRepository<Stay, Long> {

    Optional<Stay> findByRoomId(Long roomId);
    Optional<Stay> findByStayId(Long stayId);
    Optional <Stay> findByRoomIdAndStayId(Long roomId, Long stayId);
    Optional <Stay> findByUserUniqueId(Long userUniqueId);
    Optional <Stay> findByPayingGuestId(Long payingGuestId);

}
