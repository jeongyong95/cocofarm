package com.jbnu.cocofarm.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jbnu.cocofarm.domain.cart.Cart;
import com.jbnu.cocofarm.domain.cart.CartDto.CartDisplayDto;
import com.jbnu.cocofarm.domain.cart.CartDto.CartRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerUpdateDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.service.cart.CartService;
import com.jbnu.cocofarm.service.customer.CustomerService;
import com.jbnu.cocofarm.service.order.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class CustomerController {

    private CustomerService customerService;
    private CartService cartService;
    private OrderService orderService;

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

    @GetMapping(value = { "/customer/mypage", "/customer/mypage/orderList" })
    public ModelAndView mypage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        List<OrderProductDisplayDto> orderList = customerService.getPurchaseList(sessionDto);

        modelAndView.addObject("orderList", orderList);
        modelAndView.setViewName("customer/mypage");
        return modelAndView;
    }

    @GetMapping(value = "/customer/mypage/orderDetail/{orderProductId}")
    public ModelAndView orderDetail(@PathVariable Long orderProductId) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("테스트1111111111111111111111111111111111" + orderProductId);
        modelAndView.addObject("orderProductDto", orderService.findByOrderProductId(orderProductId));
        System.out.println("테스트2222222222222222222222222222222222" + orderService.findByOrderProductId(orderProductId));

        modelAndView.setViewName("customer/orderDetail");
        return modelAndView;
    }


    @PostMapping(value = "/customer/editInfo")
    public ModelAndView editInfo(HttpSession session, String password) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");

        if (customerService.checkPassword(sessionDto.getId(), password)) {
            CustomerUpdateDto updateDto = new CustomerUpdateDto(customerService.getCustomer(sessionDto.getId()));
            modelAndView.addObject("updateDto", updateDto);
            modelAndView.setViewName("customer/editInfo");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/customer/mypage");
        return modelAndView;
    }

    @PostMapping(value = "/customer/mypage/editInfoAction")
    public ModelAndView editInfoAction(HttpSession session, CustomerUpdateDto updateDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        customerService.updateCustomer(sessionDto.getId(), updateDto);

        modelAndView.setViewName("redirect:/customer/mypage");
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

    @PostMapping(value = "/customer/mypage/deleteCustomer")
    public String deleteCustomer() {
        return "customer/deleteCustomer";
    }

    @PostMapping(value = "/customer/deleteAction")
    public String deleteAction(HttpSession session) {
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        session.invalidate();
        customerService.deleteCustomer(sessionDto.getId());
        return "redirect:/index";
    }

    @PostMapping(value = "/customer/addToCart")
    public ModelAndView addToCart(HttpSession session, CartRegisterDto registerDto) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        cartService.registerCart(registerDto, sessionDto.getId());
        modelAndView.setViewName("redirect:/customer/cart");
        return modelAndView;
    }

    @GetMapping(value = "/customer/cart")
    public ModelAndView cart(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        CustomerSessionDto sessionDto = (CustomerSessionDto) session.getAttribute("customer");
        List<CartDisplayDto> displayList = cartService.findByCustomer(sessionDto.getId());
        List<Long> cartIdList = new ArrayList<>();

        int totalPrice = 0;
        for (int i = 0; i < displayList.size(); i++) {
            totalPrice += displayList.get(i).getProductTotalPrice();
            cartIdList.add(displayList.get(i).getId());
        }

        modelAndView.addObject("displayList", displayList);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("cartIdList", cartIdList);
        modelAndView.setViewName("customer/cart");
        return modelAndView;
    }

    @PostMapping(path = "/customer/cart/deleteCartAction")
    public ModelAndView deleteCartAction(Long cartId) {
        ModelAndView modelAndView = new ModelAndView();
        cartService.deleteCart(cartId);
        modelAndView.setViewName("redirect:/customer/cart");
        return modelAndView;
    }

    // 타임리프에서 리스트를 받아오도록 해야한다ㅠㅠ
    @PostMapping(path = "/customer/cart/orderFromCart")
    public ModelAndView orderFromCart(List<String> cartIdList) {
        ModelAndView modelAndView = new ModelAndView();
        List<OrderProductRegisterDto> productRegisterDtoList = new ArrayList<>();
        for (int i = 0; i < cartIdList.size(); i++) {
            Cart cart = cartService.getCart(Long.parseLong(cartIdList.get(i)));
            Product cartProduct = cart.getProductDetail().getProduct();
            OrderProductRegisterDto productRegisterDto = new OrderProductRegisterDto();
            productRegisterDto.setProduct(cartProduct);
            productRegisterDto.setQuantity(cart.getQuantity());
            productRegisterDto.setPrice(cartProduct.getPrice());
            productRegisterDto.setProductId(cartProduct.getId());
            productRegisterDto.setProductName(cartProduct.getName());
            productRegisterDto.setProductTotalPrice(productRegisterDto.getPrice() * productRegisterDto.getQuantity());
            productRegisterDtoList.add(productRegisterDto);
        }
        // cardIdList에 있는 string type의 cart id를 가지고 상품별 주문 DTO를 생성한다.
        // 생성한 상품별 주문 DTO를 productRegisterDtoList에 담는다

        modelAndView.addObject("productRegisterDtoList", productRegisterDtoList);
        modelAndView.addObject("totalRegisterDto", new OrderTotalRegisterDto());
        modelAndView.setViewName("customer/orderFromCart");
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
}