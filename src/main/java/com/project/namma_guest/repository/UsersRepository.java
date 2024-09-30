package com.project.namma_guest.repository;

import com.project.namma_guest.model.Users;
import com.vividsolutions.jts.geom.Point;
import org.locationtech.jts.algorithm.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Boolean existsByEmail(String email);
    Users findByEmail(String email);
}