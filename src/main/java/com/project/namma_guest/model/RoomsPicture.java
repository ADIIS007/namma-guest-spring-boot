package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.ast.tree.from.LazyTableGroup;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomsPicture {
    @Id
    @SequenceGenerator(
            name="picture_seq",
            sequenceName = "picture_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picture_seq"
    )
    private Long pictureId;
    private Long payingGuestRoomId;
    private String picturePath;
    private String pictureDescription;


}
