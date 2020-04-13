package com.jbnu.cocofarm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.user.Seller;
import com.jbnu.cocofarm.service.ProductService;
import com.jbnu.cocofarm.service.SellerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SellerController {

    private SellerService service;
    private ProductService productService;

    public SellerController(SellerService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping(value = "/salesManagement")
    public String salesManagement() {
        return "salesManagement";
    }

    @GetMapping(value = "/loginSeller")
    public ModelAndView loginSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("testLoginSeller");
        return modelAndView;
    }

    @PostMapping(value = "/loginSellerAction")
    public ModelAndView loginSellerAction(ModelAndView modelAndView, HttpSession session, String sellerCode,
            String password) {
        Boolean result = service.checkLogin(sellerCode, password);
        System.out.println(result);
        if (result) {
            session.setAttribute("loginedSeller", service.getSeller(sellerCode));
            modelAndView.setViewName("redirect:/salesManagement");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/loginSeller");
        return modelAndView;
    }

    @GetMapping(value = "/joinSeller")
    public ModelAndView joinSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("testJoinSeller");
        return modelAndView;
    }

    @PostMapping(value = "/joinSellerAction")
    public ModelAndView joinSellerAction(ModelAndView modelAndView, Seller seller) {
        Boolean result = service.isAlreadyJoined(seller);
        if (result) {
            System.out.println("이미 존재하는 계정이에용");
            modelAndView.setViewName("redirect:/joinSeller");
            return modelAndView;
        }
        service.registerSeller(seller);
        modelAndView.setViewName("testLoginSeller");
        return modelAndView;
    }

    @GetMapping(value = "/salesManagement/registerProduct")
    public ModelAndView registerProduct(ModelAndView modelAndView) {
        modelAndView.setViewName("registerProduct");
        return modelAndView;
    }

    @PostMapping(value = "/salesManagement/registerProductAction")
    public ModelAndView registerProductAction(ModelAndView modelAndView, @RequestPart MultipartFile files,
            HttpSession session, Product product) throws IOException {
        String saveDirectory = "/Users/jeongyong/Desktop/server/";
        files.transferTo(new File(saveDirectory + files.getOriginalFilename()));

        product.setSeller((Seller) session.getAttribute("loginedSeller"));
        productService.registerProduct(product);
        modelAndView.setViewName("redirect:/salesManagement");
        return modelAndView;
    }

}