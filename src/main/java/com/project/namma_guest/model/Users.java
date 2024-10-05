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
