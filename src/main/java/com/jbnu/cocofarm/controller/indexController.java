package com.jbnu.cocofarm.controller;

import com.jbnu.cocofarm.domain.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class indexController {

    @GetMapping(value = "/home")
    public ModelAndView index(ModelAndView modelAndView) {
        User testUser = new User("jeongyong95", "vmfhgod11");

        modelAndView.addObject("user", testUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}