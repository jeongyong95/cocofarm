package com.jbnu.cocofarm.controller.product;

import com.jbnu.cocofarm.service.product.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;

    @GetMapping(value = { "/", "/index" })
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("displayDtoList", productService.findAll());
        modelAndView.setViewName("/product/index");
        return modelAndView;
    }

    @GetMapping(value = "/product/productDetailView/{productId}")
    public ModelAndView productDetailView(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("displayDto", productService.findById(productId));
        modelAndView.setViewName("product/detail");
        return modelAndView;
    }
}