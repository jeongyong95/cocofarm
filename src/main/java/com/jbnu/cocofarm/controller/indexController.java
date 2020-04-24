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

    @GetMapping(value = "/productDetailView/{productId}")
    public ModelAndView productDetailView(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.searchProductById(productId);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("productDetail");
        return modelAndView;
    }

    @GetMapping(value = "/searchProduct")
    public ModelAndView searchProductByKeyword(String searchKeyword) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.searchProductByName(searchKeyword);

        modelAndView.addObject("searchKeyword", searchKeyword);
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("searchProduct");
        return modelAndView;
    }

    // @GetMapping(value = "/searchProduct")
    // public List<Product> searchProductByKeyword(String searchKeyword) {
    // List<Product> productList =
    // productService.searchProductByName(searchKeyword);

    // return productList;
    // }



}