package com.project.namma_guest.repository;

import com.project.namma_guest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Boolean existsByEmail(String email);
    Users findByEmail(String email);
    Optional<Users> findByUserUniqueId(long userUniqueId);
}