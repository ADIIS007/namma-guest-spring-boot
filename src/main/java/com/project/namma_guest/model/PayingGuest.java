package com.project.namma_guest.model;

import com.project.namma_guest.data.Sharing;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayingGuest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long payingGuestId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String contactNumber;
    private String email;
    private Integer advanceAmount;
    private Integer securityDeposit;
    private Sharing sharing;
    private Double latitude;
    private Double longitude;
}
