package com.jbnu.cocofarm.controller.product;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductReviewDto.ReviewRegisterDto;
import com.jbnu.cocofarm.service.customer.CustomerService;
import com.jbnu.cocofarm.service.product.ProductService;
import com.jbnu.cocofarm.service.review.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;
    private CustomerService customerService;
    private ReviewService reviewService;

    @GetMapping(value = { "/", "/index" })
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("displayDtoList", productService.findAll());
        modelAndView.setViewName("product/index");
        return modelAndView;
    }

    @GetMapping(value = "/product/answer/{questionId}")
    public ModelAndView productAnswer(@PathVariable Long questionId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionDto", productService.findQuestion(questionId));
        modelAndView.setViewName("seller/answer");
        return modelAndView;
    }

    @GetMapping(value = "/product/productDetailView/{productId}")
    public ModelAndView productDetailView(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("displayDto", productService.findById(productId));
        modelAndView.addObject("questionList", productService.getQuestionList(productId));
        modelAndView.addObject("reviewDisplayList",
                reviewService.getDisplayDtoList(productService.getProductDetail(productId).getId()));
        modelAndView.addObject("reviewRegisterDto", new ReviewRegisterDto());

        modelAndView.setViewName("product/detail");
        return modelAndView;
    }

    @PostMapping(value = "/product/productDetailView/{productId}/registerReviewAction")
    public ModelAndView registerReview(@PathVariable Long productId, HttpSession session,
            ReviewRegisterDto reviewRegisterDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");

        reviewRegisterDto.setCustomer(customerService.getCustomer(sessionDto.getId()));
        reviewRegisterDto.setProductDetail(productService.getProductDetail(productId));
        reviewService.registerReview(reviewRegisterDto);
        modelAndView.setViewName("redirect:/product/productDetailView/" + productId);
        return modelAndView;
    }

    @PostMapping(value = "/product/productDetailView/question")
    public ModelAndView productQuestionAction(HttpSession session, QuestionDto questionDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");

        productService.registerQuestion(questionDto, sessionDto.getId());
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    @GetMapping(value = "/product/searchProduct")
    public ModelAndView searchProduct(String searchKeyword) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productDisplayList", productService.searchProductName(searchKeyword));
        modelAndView.addObject("searchKeyword", searchKeyword);
        modelAndView.setViewName("product/search");
        return modelAndView;
    }
}