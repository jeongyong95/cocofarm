package com.jbnu.cocofarm.controller;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class indexController {

    private ProductService productService;

    @GetMapping(value = { "/", "/index" })
    public ModelAndView index(ModelAndView modelAndView) {
        List<Product> productList = productService.getAllProducts();

        modelAndView.addObject("template", "fragments/content/main");
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = { "/about" })
    public ModelAndView about(ModelAndView modelAndView) {
        modelAndView.addObject("content", "fragments/content/aboutDetail");

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = { "/index/productDetailView", "/productDetailView" })
    public ModelAndView productDetailView(Long productNumber) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.searchProductById(productNumber);

        // if (product == null) {
        // modelAndView.addObject("errorMessage", "찾는 상품이 없습니다.");
        // modelAndView.setViewName("redirect:/index");
        // return modelAndView;
        // }

        modelAndView.addObject("product", product);
        modelAndView.setViewName("productDetail");
        return modelAndView;
    }
}