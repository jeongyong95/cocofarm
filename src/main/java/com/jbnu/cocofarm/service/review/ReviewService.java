package com.jbnu.cocofarm.service.review;

import java.util.List;

import com.jbnu.cocofarm.domain.review.Review;

/**
 * ReivewService
 */
public interface ReviewService {
    void registerReview(Review review);

    void updateReview(Review review);

    void deleteReview(Long id);

    List<Review> searchReviewByStarPoint(Integer starPoint);
}