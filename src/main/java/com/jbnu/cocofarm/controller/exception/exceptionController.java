package com.jbnu.cocofarm.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ControllerAdvice
public class exceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView hadleAllExceptions() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("exception");
        return modelAndView;
    }
}