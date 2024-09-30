package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Stay {
    @Id
    @SequenceGenerator(
            name="stay_seq",
            sequenceName = "stay_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stay_seq"
    )
    private Long stayId;
    private Long userUniqueId;
    private Long payingGuestId;
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private String comment;
    private int rating;


}
