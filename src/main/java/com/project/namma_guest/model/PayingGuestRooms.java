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

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PayingGuestRooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
