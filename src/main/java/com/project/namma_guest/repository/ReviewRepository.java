package com.project.namma_guest.repository;

import com.project.namma_guest.model.review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<review, Long> {
}
