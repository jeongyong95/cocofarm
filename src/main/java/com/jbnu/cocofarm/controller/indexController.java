package com.jbnu.cocofarm.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.asisstant.Role;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class indexController {

    @Autowired
    private UserService userservice;

    @GetMapping(value = "/")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.addObject("template", "fragments/content/main");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/loginAction")
    public ModelAndView loginAction(ModelAndView modelAndView, HttpSession session, String email, String password) {
        // 로그인 처리에 따라 index나 리디렉트로 login 반환
        Boolean result = userservice.checkLogin(email, password);
        System.out.println(result);
        if (result) {
            session.setAttribute("user", userservice.getUser(email));
            modelAndView.addObject("message", "안녕하세요!");
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("message", "아이디 혹은 비밀번호가 틀렸습니다.");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @GetMapping(value = "/join")
    public ModelAndView join(ModelAndView modelAndView) {
        modelAndView.setViewName("join");
        return modelAndView;
    }

    @PostMapping(value = "/joinAction")
    public ModelAndView joinAction(ModelAndView modelAndView, User user) {
        // 회원가입 처리에 따라 login이나 리디렉트로 join 반환
        if (userservice.isAlreadyJoined(user)) {
            modelAndView.addObject("message", "이미 존재하는 계정입니다.");
            modelAndView.setViewName("redirect:/join");
            return modelAndView;
        }
        user.setCreatedTimestamp(LocalDateTime.now());
        userservice.createUser(user);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}