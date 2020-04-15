package com.jbnu.cocofarm.controller;

import com.jbnu.cocofarm.domain.product.Category;
import com.jbnu.cocofarm.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AdminController {

    private CategoryService categoryService;

    @GetMapping(value = "/admin/category")
    public ModelAndView category() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("category");
        return modelAndView;
    }

    @PostMapping(value = "/admin/category/categoryAction")
    public ModelAndView registerCategory(Category category) {
        ModelAndView modelAndView = new ModelAndView();
        categoryService.registerCategory(category);
        modelAndView.setViewName("redirect:/category");
        return modelAndView;
    }
}