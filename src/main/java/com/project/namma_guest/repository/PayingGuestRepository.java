package com.project.namma_guest.repository;

import com.project.namma_guest.model.PayingGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayingGuestRepository extends JpaRepository<PayingGuest, Long> {
}
