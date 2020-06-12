package com.jbnu.cocofarm.service.review;

import java.util.ArrayList;
import java.util.List;

import com.jbnu.cocofarm.domain.product.ProductReview;
import com.jbnu.cocofarm.domain.product.dto.ProductReviewDto.ReviewDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductReviewDto.ReviewRegisterDto;
import com.jbnu.cocofarm.domain.product.repository.ProductDetailRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductReviewRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    private ProductReviewRepository reviewRepo;
    private ProductDetailRepository detailRepo;

    @Override
    public void registerReview(ReviewRegisterDto registerDto) {
        reviewRepo.save(registerDto.toEntity());
    }

    @Override
    public void updateReview() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteReview() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ReviewDisplayDto> getDisplayDtoList(Long detailId) {
        List<ReviewDisplayDto> reviewDisplayList = new ArrayList<>();
        List<ProductReview> reviewList = reviewRepo.findByProductDetail(detailRepo.getOne(detailId));

        for (ProductReview productReview : reviewList) {
            ReviewDisplayDto displayDto = new ReviewDisplayDto();

            displayDto.setReviewId(productReview.getId());
            displayDto.setTitle(productReview.getTitle());
            displayDto.setContent(productReview.getContent());
            displayDto.setStarPoint(productReview.getStarPoint());
            displayDto.setCreatedTimestamp(productReview.getCreatedTimestamp());
            displayDto.setCustomerId(productReview.getCustomer().getId());
            displayDto.setCustomerName(productReview.getCustomer().getName());
            displayDto.setDetailId(detailId);

            reviewDisplayList.add(displayDto);
        }
        return reviewDisplayList;
    }

}