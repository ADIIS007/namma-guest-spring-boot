package com.project.namma_guest.repository;

import com.project.namma_guest.model.PayingGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface PayingGuestRepository extends JpaRepository<PayingGuest, Long> {
    @Query(value = "SELECT * FROM paying_guest WHERE ST_DWithin(location, ST_SetSRID(ST_MakePoint(?1, ?2), 4326), ?3)", nativeQuery = true)
    List<PayingGuest> findWithinDistance(double longitude, double latitude, double distanceInMeters);
    Boolean existsByEmail(String email);
    PayingGuest findPayingGuestByPayingGuestId(Long paymentGuestId);
    List<PayingGuest> findAll(Pageable pageable);
}