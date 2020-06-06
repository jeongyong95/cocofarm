package com.jbnu.cocofarm.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.customer.CustomerRepository;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.ProductQuestion;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailUpdateDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.AnswerQuestionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDto;
import com.jbnu.cocofarm.domain.product.repository.ProductDetailRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductQuestionRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductRepository;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerRepository;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductDetailRepository detailRepo;
    private CustomerRepository customerRepo;
    private ProductQuestionRepository questionRepo;
    private SellerRepository sellerRepo;

    @Override
    public void registerProduct(ProductRegisterDto registerDto, DetailRegisterDto detailDto) {
        detailRepo.save(detailDto.toEntity(productRepo.save(registerDto.toEntity())));
    }

    @Override
    public List<ProductDisplayDto> findBySeller(Seller seller) {
        List<Product> productList = productRepo.findBySeller(seller);
        List<ProductDisplayDto> displayDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            ProductDisplayDto displayDto = new ProductDisplayDto(productList.get(i));
            displayDtoList.add(displayDto);
        }
        return displayDtoList;
    }

    @Override
    public List<ProductDisplayDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDisplayDto> displayDtoList = modelMapper.map(productRepo.findAll(),
                new TypeToken<List<ProductDisplayDto>>() {
                }.getType());
        return displayDtoList;
    }

    @Override
    public ProductDisplayDto findById(Long id) {
        return new ProductDisplayDto(productRepo.findById(id).get());
    }

    @Override
    public void updateStock(int orderedQuantity, Long ProductId) {
        ProductDetail updatedDetail = productRepo.findById(ProductId).get().getProductDetail();
        DetailUpdateDto updateDto = new DetailUpdateDto(updatedDetail);
        updateDto.setStock(updateDto.getStock() - orderedQuantity);
        updatedDetail.updateDetail(updateDto);
    }

    @Override
    public void registerQuestion(QuestionDto questionDto, Long customerId) {
        questionDto.setCustomer(customerRepo.getOne(customerId));
        questionDto.setProduct(productRepo.getOne(questionDto.getProductId()));
        questionRepo.save(questionDto.toEntity());

    }

    @Override
    public List<QuestionDto> getQuestion(SellerSessionDto sellerSessionDto) {

        ModelMapper modelMapper = new ModelMapper();
        Seller seller = sellerRepo.findById(sellerSessionDto.getId()).get();
        List<Product> productList = productRepo.findBySeller(seller);
        List<QuestionDto> questionList = new ArrayList<QuestionDto>();

        for (int i = 0; i < productList.size(); i++) {

            List<QuestionDto> question = modelMapper.map(questionRepo.findByProduct(productList.get(i)),
                    new TypeToken<List<QuestionDto>>() {
                    }.getType());

            questionList.addAll(question);
        }

        return questionList;
    }

    @Override
    public QuestionDisplayDto findQuestion(Long id) {
        ProductQuestion question = questionRepo.findById(id).get();
        QuestionDisplayDto questionDisplayDto = new QuestionDisplayDto(question);
        return questionDisplayDto;
    }

    @Override
    public void answerQuestion(AnswerQuestionDto answerDto, Long sellerId) {
        answerDto.setSeller(sellerRepo.getOne(sellerId));
        answerDto.setProduct(productRepo.getOne(answerDto.getProductId()));
        answerDto.setGroupLevel(1);
        questionRepo.save(answerDto.toEntity());

    }

    @Override
    public List<QuestionDisplayDto> getQuestionList(Long productId) {

        Product product = productRepo.getOne(productId);

        List<ProductQuestion> questions = questionRepo.findByProduct(product);
        List<QuestionDisplayDto> questionList = new ArrayList<QuestionDisplayDto>();

        for (int i = 0; i < questions.size(); i++) {

            if (questions.get(i).getGroupNumber() == null) {
                QuestionDisplayDto questionDisplayDto = new QuestionDisplayDto(questions.get(i));
                questionList.add(questionDisplayDto);

                ProductQuestion answer = questionRepo.findByGroupNumber(questionDisplayDto.getId());
                if (answer != null) {
                    QuestionDisplayDto answerDisplayDto = new QuestionDisplayDto(answer);
                    questionList.add(answerDisplayDto);
                }
            }

        }

        return questionList;
    }

}