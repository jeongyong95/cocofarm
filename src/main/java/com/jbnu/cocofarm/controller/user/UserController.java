package com.jbnu.cocofarm.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.domain.user.UserDto.UserRequestDto;
import com.jbnu.cocofarm.service.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController {

    private UserService service;

    @GetMapping(value = "/user/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    @PostMapping(value = "/user/loginAction")
    public ModelAndView loginAction(ModelAndView modelAndView, HttpSession session, UserRequestDto userRequestDto) {
        // 로그인 처리에 따라 index나 리디렉트로 login 반환
        Boolean result = service.checkLogin(userRequestDto);
        System.out.println(result);
        if (result) {
            session.setAttribute("user", service.getUser(userRequestDto.getEmail()));
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }
        modelAndView.addObject("message", "아이디 혹은 비밀번호가 틀렸습니다.");
        modelAndView.setViewName("redirect:/user/login");
        return modelAndView;
    }

    @GetMapping(value = "/user/join")
    public ModelAndView join(ModelAndView modelAndView) {
        modelAndView.setViewName("/user/join");
        return modelAndView;
    }

    @PostMapping(value = "/user/joinAction")
    public ModelAndView joinAction(ModelAndView modelAndView, UserRequestDto userRequestDto) {
        // 회원가입 처리에 따라 login이나 리디렉트로 join 반환
        if (service.isAlreadyJoined(userRequestDto.getEmail()))

        {
            modelAndView.addObject("message", "이미 존재하는 계정입니다.");
            modelAndView.setViewName("redirect:/user/join");
            return modelAndView;
        }
        service.registerUser(userRequestDto);
        modelAndView.setViewName("/user/login");
        return modelAndView;
    }

    @GetMapping(value = "/user/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        session.invalidate();
        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    @GetMapping(value = "/user/basket")
    public ModelAndView basket(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<Basket> basketList = service.getMyBasket(user);

        List<Basket> listMap = basketList;

        int totalPrice = 0;

        for (int i = 0; i < listMap.size(); i++) {

            int p = listMap.get(i).getProductDetail().getProduct().getPrice();
            int c = listMap.get(i).getCount();

            totalPrice += (p * c);
        }

        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("basketList", basketList);
        modelAndView.setViewName("/user/basket");
        return modelAndView;
    }

    @GetMapping(value = "/user/orders")
    public ModelAndView orders(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");
        List<OrdersTemp> ordersTempList = service.getMyOrdersTemp(user);

        List<OrdersTemp> listMap = ordersTempList;

        int totalPrice = 0;

        for (int i = 0; i < listMap.size(); i++) {

            int p = listMap.get(i).getProductDetail().getProduct().getPrice();
            int c = listMap.get(i).getCount();

            totalPrice += (p * c);
        }

        modelAndView.addObject("user", user);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("ordersTempList", ordersTempList);
        modelAndView.setViewName("/user/orders");
        return modelAndView;
    }
}