package com.project.namma_guest.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Lazy;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class review {
    @Id
    @SequenceGenerator(
            name="review_seq",
            sequenceName = "review_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_seq"
    )
    private Long reviewId;
    private int rating;
    private String review_comment;



}
