package com.jbnu.cocofarm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class indexController {

    @GetMapping(value = { "/", "/index" })
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.addObject("template", "fragments/content/main");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}