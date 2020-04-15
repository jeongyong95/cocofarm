package com.jbnu.cocofarm.controller;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.service.BasketService;
import com.jbnu.cocofarm.service.ProductService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class OrdersController {

    private ProductService productService;
    private BasketService basketService;

    @PostMapping(value = "/productDetailView/insertIntoBasket")
    public ModelAndView insertIntoBasket(HttpSession session, Basket basket, Long productDetailId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        ProductDetail productDetail = productService.searchProductDetailById(productDetailId);
        basket.setProductDetail(productDetail);
        basket.setUser(user);

        basketService.registerBasket(basket);
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

}