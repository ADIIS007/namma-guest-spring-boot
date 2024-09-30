package com.project.namma_guest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomsPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pictureId;
    private Long payingGuestRoomId;
    private String picturePath;
    private String pictureDescription;
}
