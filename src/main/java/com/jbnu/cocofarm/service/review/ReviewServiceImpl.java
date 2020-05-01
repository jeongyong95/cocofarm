package com.jbnu.cocofarm.service.review;

import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.review.Review;
import com.jbnu.cocofarm.domain.review.ReviewRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * ReviewServiceImpl
 */
@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository repo;

    @Override
    public void registerReview(Review review) {
        repo.save(review);
        System.out.println("리뷰저장 성공");
    }

    @Override
    public void updateReview(Review review) {
        repo.save(review);
        System.out.println("리뷰수정 성공");
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> findedReview = repo.findById(id);
        findedReview.ifPresent(target -> {
            repo.delete(target);
            System.out.println("리뷰삭제 성공");
        });
    }

    @Override
    public List<Review> searchReviewByStarPoint(Integer starPoint) {
        List<Review> searchResultList = repo.findByStarPointGreaterThanEqual(starPoint);
        return searchResultList;
    }

}