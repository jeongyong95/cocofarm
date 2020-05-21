package com.jbnu.cocofarm.controller.customer;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;
import com.jbnu.cocofarm.service.customer.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class CustomerController {

    private CustomerService customerService;

    @GetMapping(value = "/customer/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/login");
        return modelAndView;
    }

    @GetMapping(value = "/customer/join")
    public ModelAndView join() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registerDto", new CustomerRegisterDto());
        modelAndView.setViewName("customer/join");
        return modelAndView;
    }

    @GetMapping(value = "/customer/mypage")
    public String mypage() {
        return "customer/mypage";
    }

    @GetMapping(value = "/customer/cart")
    public ModelAndView cart(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");

        // cartDisplayDtoList를 담아서 보내야 함
        // modelAndView.addObject(attributeName, attributeValue)
        modelAndView.setViewName("customer/cart");
        return modelAndView;
    }

    @PostMapping(value = "/customer/loginAction")
    public ModelAndView loginAction(HttpSession session, CustomerLoginDto loginDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = customerService.checkCustomer(loginDto);
        if (sessionDto != null) {
            session.setAttribute("customer", sessionDto);
            modelAndView.setViewName("redirect:/index");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/customer/login");
        return modelAndView;
    }

    @GetMapping(value = "/customer/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }

    @PostMapping(value = "/customer/joinAction")
    public ModelAndView joinAction(@Valid CustomerRegisterDto registerDto, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("customer/join");
            return modelAndView;
        }

        if (customerService.registerCustomer(registerDto)) {
            modelAndView.setViewName("redirect:/customer/login");
            return modelAndView;
        }
        modelAndView.addObject("message", "이미 존재하는 계정입니다!");
        modelAndView.setViewName("redirect:/customer/join");
        return modelAndView;
    }

    @PostMapping(value = "/customer/order/new")
    public ModelAndView orderNew(OrderProductRegisterDto productRegisterDto) {
        ModelAndView modelAndView = new ModelAndView();
        productRegisterDto.setProductTotalPrice(productRegisterDto.getPrice() * productRegisterDto.getQuantity());

        modelAndView.addObject("productRegisterDto", productRegisterDto);
        modelAndView.addObject("totalRegisterDto", new OrderTotalRegisterDto());
        modelAndView.setViewName("customer/order");
        return modelAndView;
    }

    @PostMapping(value = "/customer/addToCart")
    public ModelAndView addToCart() {
        ModelAndView modelAndView = new ModelAndView();

        // 장바구니에 담는 로직 구현하기
        modelAndView.setViewName("redirect:/customer/cart");
        return modelAndView;
    }
}