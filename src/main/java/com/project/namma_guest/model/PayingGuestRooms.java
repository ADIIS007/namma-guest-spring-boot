package com.project.namma_guest.model;

import com.project.namma_guest.data.Sharing;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PayingGuestRooms {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long payingGuestRoomId;
    private Long payingGuestId;
    private String roomNumber;
    private Sharing roomType;
    private boolean isAvailable;
    private Date availableFrom;
    // monthly needed to be paid
    private double rent;
    // will be returned after vacating
    private double securityDeposit;
    // will not be returned after vacating
    private double maintenance;
    private boolean isVerified;
}
