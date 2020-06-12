package com.jbnu.cocofarm.controller.seller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.delivery.DeliveryDto.DeliveryRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.AnswerQuestionDto;
import com.jbnu.cocofarm.domain.product.dto.ProductQuestionDto.QuestionDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerLoginDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;
import com.jbnu.cocofarm.service.delivery.DeliveryService;
import com.jbnu.cocofarm.service.product.ProductService;
import com.jbnu.cocofarm.service.seller.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SellerController {

    private SellerService sellerService;
    private ProductService productService;
    private DeliveryService deliveryService;

    @GetMapping(value = "/seller/salesManagement")
    public ModelAndView saleManagement(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        SellerSessionDto sessionDto = (SellerSessionDto) session.getAttribute("seller");
        List<QuestionDto> questionList = productService.getQuestion(sessionDto);
        List<ProductDisplayDto> productList = productService.findBySeller(sellerService.getSeller(sessionDto.getId()));
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("questionList", questionList);
        modelAndView.setViewName("seller/salesManagement");
        return modelAndView;
    }

    @GetMapping(value = "/seller/salesManagement/saleList")
    public ModelAndView saleList(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        SellerSessionDto sellerSessionDto = (SellerSessionDto) session.getAttribute("seller");
        List<OrderProductDisplayDto> saleList = sellerService.getSaleList(sellerSessionDto);

        modelAndView.addObject("saleList", saleList);
        modelAndView.setViewName("seller/saleList");
        return modelAndView;
    }

    @GetMapping(value = "/seller/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/login");
        return modelAndView;
    }

    @GetMapping(value = "/seller/join")
    public ModelAndView join() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/join");
        return modelAndView;
    }

    @PostMapping(value = "/seller/loginAction")
    public ModelAndView loginAction(HttpSession session, SellerLoginDto loginDto) {
        ModelAndView modelAndView = new ModelAndView();
        SellerSessionDto sessionDto = sellerService.checkSeller(loginDto);
        if (sessionDto != null) {
            session.setAttribute("seller", sessionDto);
            modelAndView.setViewName("redirect:/seller/salesManagement");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/seller/login");
        return modelAndView;
    }

    @PostMapping(value = "/seller/joinAction")
    public ModelAndView joinAction(SellerRegisterDto registerDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (sellerService.registerSeller(registerDto)) {
            modelAndView.setViewName("redirect:/seller/login");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/seller/join");
        return modelAndView;
    }

    @GetMapping(value = "/seller/salesManagement/registerProduct")
    public ModelAndView registerProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productRegisterDto", new ProductRegisterDto());
        modelAndView.setViewName("seller/registerProduct");
        return modelAndView;
    }

    @PostMapping(value = "/seller/salesManagement/registerProductAction")
    public String registerProductAction(HttpSession session, ProductRegisterDto registerDto,
            DetailRegisterDto detailDto) {
        SellerSessionDto sessionDto = (SellerSessionDto) session.getAttribute("seller");
        registerDto.setSeller(sellerService.getSeller(sessionDto.getId()));
        productService.registerProduct(registerDto, detailDto);
        return "redirect:/seller/salesManagement";
    }

    @GetMapping(value = "/seller/registerDelivery")
    public ModelAndView registerDelivery(Long orderProductId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orderProductId", orderProductId);
        modelAndView.addObject("deliveryRegisterDto", new DeliveryRegisterDto());

        modelAndView.setViewName("seller/registerDelivery");
        return modelAndView;
    }

    @PostMapping(value = "/seller/registerDeliveryAction")
    public String registerDeliveryAction(DeliveryRegisterDto deliveryRegisterDto, Long orderProductId) {
        deliveryService.registerDelivery(deliveryRegisterDto, orderProductId);
        return "redirect:/seller/salesManagement";
    }

    @PostMapping(value = "/seller/questionAction")
    public ModelAndView productQuestionAction(HttpSession session, AnswerQuestionDto answerDto) {
        ModelAndView modelAndView = new ModelAndView();
        SellerSessionDto sessionDto = (SellerSessionDto) session.getAttribute("seller");
        productService.answerQuestion(answerDto, sessionDto.getId());

        modelAndView.setViewName("/seller/salesManagement");
        return modelAndView;
    }
}