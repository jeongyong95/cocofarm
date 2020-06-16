package com.jbnu.cocofarm.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.service.order.OrderService;
import com.jbnu.cocofarm.service.payment.KakaoPay;
import com.jbnu.cocofarm.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@AllArgsConstructor
@Controller
public class KakaoPayController {

    private ProductService productService;
    private OrderService orderService;

    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;


    @GetMapping("/payment/kakaoPay")
    public void kakaoPayGet() {

    }

    @PostMapping("/payment/kakaoPay")
    public String kakaoPay(HttpSession session, OrderTotalRegisterDto totalRegisterDto,
            OrderProductRegisterDto productRegisterDto) {

        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        totalRegisterDto.setCustomerName(sessionDto.getName());

        System.out.println("테스트00000000000000000000000000" + totalRegisterDto);
        System.out.println("테스트00000000000000000000000000" + productRegisterDto);

        session.setAttribute("totalRegisterTemp", totalRegisterDto);
        session.setAttribute("productRegisterTemp", productRegisterDto);

        return "redirect:" + kakaopay.kakaoPayReady(totalRegisterDto);

    }

    @RequestMapping(value = "/payment/kakaoPaySuccess" , method = {RequestMethod.GET, RequestMethod.POST})
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {

        OrderTotalRegisterDto totalRegisterDto = (OrderTotalRegisterDto) session.getAttribute("totalRegisterTemp");
        OrderProductRegisterDto productRegisterDto = (OrderProductRegisterDto) session
                .getAttribute("productRegisterTemp");
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");

        System.out.println("테스트11111111111111111111111111" + totalRegisterDto);
        System.out.println("테스트22222222222222222222222222" + productRegisterDto);
        System.out.println("테스트33333333333333333333333333" + sessionDto);

        List<OrderProductRegisterDto> productRegisterDtoList = new ArrayList<>();

        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, totalRegisterDto));

        productRegisterDtoList.add(productRegisterDto);
        productService.updateStock(productRegisterDto.getQuantity(), productRegisterDto.getProductId());
        orderService.registerOrder(totalRegisterDto, productRegisterDtoList, sessionDto.getId());
    }

}