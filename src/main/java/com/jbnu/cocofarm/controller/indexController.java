package com.jbnu.cocofarm.controller;

import com.jbnu.cocofarm.domain.User;
import com.jbnu.cocofarm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class indexController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping(value = "/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/loginAccess")
    public ModelAndView loginAccess(ModelAndView modelAndView, String email, String password) {
        User accessUser = new User();
        accessUser.setEmail(email);
        accessUser.setPassword(password);
        return modelAndView;
    }

    @GetMapping(value = "/join")
    public ModelAndView join(ModelAndView modelAndView) {
        modelAndView.setViewName("join");
        return modelAndView;
    }

    @PostMapping(value = "/joinAccess")
    public ModelAndView joinAccess(ModelAndView modelAndView) {
        return modelAndView;
    }

}