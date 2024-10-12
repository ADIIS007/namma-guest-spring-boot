package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PayingGuest {
    @Id
    @SequenceGenerator(
            name="paying_sequence",
            sequenceName = "paying_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="paying_sequence"
    )
    private Long payingGuestId;
    private String name;

    //Address
    private String address;
    private String city;
    private String state;
    private String country;

    //Contact
    private String email;
    private String contactNumber;

    //Location
    private Point location;
    private boolean isVerified;
    private String guestType;
}
