package com.project.namma_guest.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Locale;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class address {

    @Id
    @SequenceGenerator(
            name="address_seq",
            sequenceName = "address_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_seq"
    )
    private Long addressId;
    private String addressLine1;
    private String addressLine2;
    private Locale country;
    private String city;
    private String postalCode;


}
