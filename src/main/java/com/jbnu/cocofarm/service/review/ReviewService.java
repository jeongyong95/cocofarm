package com.jbnu.cocofarm.service.review;

import java.util.List;

import com.jbnu.cocofarm.domain.product.dto.ProductReviewDto.ReviewDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductReviewDto.ReviewRegisterDto;

public interface ReviewService {

    void registerReview(ReviewRegisterDto registerDto);

    void updateReview();

    void deleteReview();

    List<ReviewDisplayDto> getDisplayDtoList(Long detailId);
}