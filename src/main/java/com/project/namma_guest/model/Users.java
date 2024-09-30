package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userUniqueId;
    private String given_name;
    private String family_name;
    @Column(unique = true)
    private String email;
    private String contactNumber;
    private Point currentLocation;
    private Long currentPayingGuestId;
    private Long ownsPayingGuestId;
    private String OTP;
    private Date OTPGeneratedAt;
    private Boolean isVerified;
}
