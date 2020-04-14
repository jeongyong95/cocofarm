package com.jbnu.cocofarm.controller;

import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class indexController {

    private ProductService productService;

    @GetMapping(value = { "/", "/index" })
    public ModelAndView main(ModelAndView modelAndView) {
        List<Product> productList = productService.getAllProducts();

        modelAndView.addObject("template", "fragments/content/main");
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}