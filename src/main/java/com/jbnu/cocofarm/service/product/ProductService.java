package com.jbnu.cocofarm.service.product;

import java.util.List;

import com.jbnu.cocofarm.domain.domainAssistance.Category;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.AnswerQuestionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDto;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;

public interface ProductService {

    void registerProduct(ProductRegisterDto registerDto, DetailRegisterDto detailDto);

    void updateStock(int orderedQuantity, Long productId);

    ProductDisplayDto findById(Long id);

    List<ProductDisplayDto> findAll();

    List<ProductDisplayDto> findBySeller(Seller seller);

    void registerQuestion(QuestionDto questionDto, Long customerId);

    List<QuestionDto> getQuestion(SellerSessionDto sellerSessionDto);

    QuestionDisplayDto findQuestion(Long id);

    void answerQuestion(AnswerQuestionDto answerDto, Long sellerId);

    List<QuestionDisplayDto> getQuestionList(Long productId);

    ProductDetail getProductDetail(Long productId);

    List<ProductDisplayDto> searchProductName(String searchKeyword);

    List<ProductDisplayDto> searchCategory(Category category);

}