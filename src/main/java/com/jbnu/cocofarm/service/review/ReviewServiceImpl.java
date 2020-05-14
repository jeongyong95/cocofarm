package com.jbnu.cocofarm.service.review;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jbnu.cocofarm.domain.review.Review;
import com.jbnu.cocofarm.domain.review.ReviewRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * ReviewServiceImpl
 */
@AllArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepo;

    @Override
    public void registerReview(Review review) {
        reviewRepo.save(review);
        System.out.println("리뷰저장 성공");
    }

    @Override
    public void updateReview(Review review) {
        reviewRepo.save(review);
        System.out.println("리뷰수정 성공");
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> findedReview = reviewRepo.findById(id);
        findedReview.ifPresent(target -> {
            reviewRepo.delete(target);
            System.out.println("리뷰삭제 성공");
        });
    }

    @Override
    public List<Review> getReviewList(Long id) {
        return reviewRepo.findByProduct(id);
    }

    @Override
    public List<Review> searchReviewByStarPoint(Integer starPoint) {
        return reviewRepo.findByStarPointGreaterThanEqual(starPoint);
    }

}