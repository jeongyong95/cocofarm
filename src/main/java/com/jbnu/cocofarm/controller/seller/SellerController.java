package com.jbnu.cocofarm.controller.seller;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerLoginDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;
import com.jbnu.cocofarm.service.product.ProductService;
import com.jbnu.cocofarm.service.seller.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SellerController {

    private SellerService sellerService;
    private ProductService productService;

    @GetMapping(value = "/seller/salesManagement")
    public ModelAndView saleManagement() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("seller/salesManagement");
        return modelAndView;
    }

    @GetMapping(value = "/seller/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/login");
        return modelAndView;
    }

    @GetMapping(value = "/seller/join")
    public ModelAndView join() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("seller/join");
        return modelAndView;
    }

    @PostMapping(value = "/seller/loginAction")
    public ModelAndView loginAction(HttpSession session, SellerLoginDto loginDto) {
        ModelAndView modelAndView = new ModelAndView();
        SellerSessionDto sessionDto = sellerService.checkSeller(loginDto);
        if (sessionDto != null) {
            session.setAttribute("seller", sessionDto);
            modelAndView.setViewName("redirect:/seller/salesManagement");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/seller/login");
        return modelAndView;
    }

    @PostMapping(value = "/seller/joinAction")
    public ModelAndView joinAction(SellerRegisterDto registerDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (sellerService.registerSeller(registerDto)) {
            modelAndView.setViewName("redirect:/seller/login");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/seller/join");
        return modelAndView;
    }

    @GetMapping(value = "/seller/salesManagement/registerProduct")
    public ModelAndView registerProduct() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productRegisterDto", new ProductRegisterDto());
        modelAndView.setViewName("seller/registerProduct");
        return modelAndView;
    }

    @PostMapping(value = "/seller/salesManagement/registerProductAction")
    public String registerProductAction(HttpSession session, ProductRegisterDto registerDto,
            DetailRegisterDto detailDto) {
        SellerSessionDto sessionDto = (SellerSessionDto) session.getAttribute("seller");
        registerDto.setSeller(sellerService.getSeller(sessionDto.getId()));
        productService.registerProduct(registerDto, detailDto);
        return "redirect:/seller/salesManagement";
    }
}