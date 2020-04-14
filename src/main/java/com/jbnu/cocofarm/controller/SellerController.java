package com.jbnu.cocofarm.controller;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.user.Seller;
import com.jbnu.cocofarm.service.SellerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class SellerController {

    private SellerService service;

    @GetMapping(value = { "/loginSeller", "/loginseller" })
    public ModelAndView loginSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("loginSeller");
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

    @GetMapping(value = { "/joinSeller", "/joinseller" })
    public ModelAndView joinSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("joinSeller");
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
        modelAndView.setViewName("redirect:/loginSeller");
        return modelAndView;
    }

}