package com.jbnu.cocofarm.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;
import com.jbnu.cocofarm.service.order.OrderService;
import com.jbnu.cocofarm.service.product.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class PaymentController {

    private ProductService productService;
    private OrderService orderService;

    @PostMapping(value = "/payment/pay")
    public ModelAndView pay(HttpSession session, OrderTotalRegisterDto totalRegisterDto,
            OrderProductRegisterDto productRegisterDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        List<OrderProductRegisterDto> productRegisterDtoList = new ArrayList<>();

        productRegisterDtoList.add(productRegisterDto);
        productService.updateStock(productRegisterDto.getQuantity(), productRegisterDto.getProductId());
        orderService.registerOrder(totalRegisterDto, productRegisterDtoList, sessionDto.getId());
        return modelAndView;
    }

}