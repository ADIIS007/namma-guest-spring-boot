package com.project.namma_guest.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class facility {

    @Id
    @SequenceGenerator(
            name="faculty_sequence",
            sequenceName = "faculty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_sequence"
    )
    private long facilityId;
    private String facilityName;
    private String facilityDescription;



}
