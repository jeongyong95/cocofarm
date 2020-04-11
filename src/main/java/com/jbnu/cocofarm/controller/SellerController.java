package com.jbnu.cocofarm.controller;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.user.Seller;
import com.jbnu.cocofarm.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SellerController {

    @Autowired
    private SellerService service;

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
            modelAndView.setViewName("salesManagement");
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
}