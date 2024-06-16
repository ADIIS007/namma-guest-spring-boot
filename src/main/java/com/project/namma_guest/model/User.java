package com.project.namma_guest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private Long userId;
    private String name;
    private String email;
    private String contactNumber;
}
