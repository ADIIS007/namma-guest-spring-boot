package com.project.namma_guest.DTO.Request;

import com.project.namma_guest.data.LatLng;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hostel {
    //Hostel Name
    private String name;

    //Hostel Address
    private String address;
    private String city;
    private String state;
    private String country;

    //Hostel Contact
    private String email;
    private String contactNumber;
    private String whatsappNumber;

    //Hostel Location
    private LatLng location;
}
