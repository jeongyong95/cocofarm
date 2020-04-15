package com.jbnu.cocofarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserController {

    private UserService service;

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/loginAction")
    public ModelAndView loginAction(ModelAndView modelAndView, HttpSession session, String email, String password) {
        // 로그인 처리에 따라 index나 리디렉트로 login 반환
        Boolean result = service.checkLogin(email, password);
        System.out.println(result);
        if (result) {
            session.setAttribute("user", service.getUser(email));
            modelAndView.setViewName("redirect:/index");
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
        if (service.isAlreadyJoined(user)) {
            modelAndView.addObject("message", "이미 존재하는 계정입니다.");
            modelAndView.setViewName("redirect:/join");
            return modelAndView;
        }
        service.registerUser(user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        session.invalidate();
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    @GetMapping(value = "/basket")
    public ModelAndView basket(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("loginedUser");
        List<Basket> basketList = service.getMyBasket(user);

        modelAndView.addObject("basketList", basketList);
        modelAndView.setViewName("basket");
        return modelAndView;
    }
}