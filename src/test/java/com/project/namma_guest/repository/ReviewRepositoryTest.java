package com.project.namma_guest.repository;

import com.project.namma_guest.model.review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        review review = new review();
        review.setReviewId(1L);
        review.setReview_comment("Review comment");
        review.setRating(5);

        reviewRepository.save(review);
    }

    @Test
    public void testReviewId(){
        Optional<review> review1 = reviewRepository.findById(1L);
        assertTrue(review1.isPresent());
        assertEquals(review1.get().getReviewId(), 1L);
    }
    @Test
    public void testReviewComment(){
        Optional<review> review1 = reviewRepository.findById(1L);
        assertTrue(review1.isPresent());
        assertEquals(review1.get().getReview_comment(), "Review comment");
    }
    @Test
    public void testUpdateReviewRating(){
        Optional<review> review1 = reviewRepository.findById(1L);
        assertTrue(review1.isPresent());
        review r = review1.get();
        r.setRating(4);
        reviewRepository.save(r);
        Optional<review> review2 = reviewRepository.findById(1L);
        assertTrue(review2.isPresent());
        assertEquals(review2.get().getRating(), 4);
    }
    @Test
    public void testDeleteReview(){
        Optional<review> review1 = reviewRepository.findById(1L);
        assertTrue(review1.isPresent());
        reviewRepository.delete(review1.get());
        Optional<review> review2 = reviewRepository.findById(1L);
        assertFalse(review2.isPresent());
    }
}