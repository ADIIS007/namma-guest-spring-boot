package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userUniqueId;

    //personal data
    private String given_name;
    private String family_name;

    //contact information
    @Column(unique = true)
    private String email;
    private String contactNumber;
    private Point currentLocation;

    //Relational properties
    private String userType;
    @ManyToOne
    @JoinColumn(name = "current_paying_guest_id")
    private PayingGuest currentPayingGuest;
    @OneToOne
    @JoinColumn(name = "owns_paying_guest_id")
    private PayingGuest ownsPayingGuest;

    //verification methods
    private String OTP;
    private Date OTPGeneratedAt;
    private Boolean isVerified;
}