package com.project.namma_guest.repository;

import com.project.namma_guest.model.address;
import jakarta.mail.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<address, Long> {
}
