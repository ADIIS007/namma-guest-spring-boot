package com.project.namma_guest.model;

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
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stayId;
    private Long userUniqueId;
    private Long payingGuestId;
    private Long roomId;
    private Date startDate;
    private Date endDate;
    private String comment;
    private int rating;

}
