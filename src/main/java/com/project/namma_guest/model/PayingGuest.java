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
import org.locationtech.jts.geom.Point;

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