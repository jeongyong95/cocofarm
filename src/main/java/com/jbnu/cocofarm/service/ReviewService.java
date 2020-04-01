package com.jbnu.cocofarm.service;

import java.util.List;

import com.jbnu.cocofarm.domain.user.Review;

/**
 * ReivewService
 */
public interface ReviewService {
    void saveReview(Review review);

    void updateReview(Review review);

    void deleteReview(Long id);

    List<Review> searchReviewByStarPoint(Integer starPoint);
}