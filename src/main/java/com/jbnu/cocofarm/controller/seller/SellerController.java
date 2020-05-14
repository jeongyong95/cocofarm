package com.jbnu.cocofarm.controller.seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDto.ProductRequestDto;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.service.product.ProductService;
import com.jbnu.cocofarm.service.seller.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class SellerController {

    private SellerService sellerService;
    private ProductService productService;

    @GetMapping(value = { "/seller/loginSeller", "/seller/loginseller" })
    public ModelAndView loginSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("/seller/loginSeller");
        return modelAndView;
    }

    @PostMapping(value = "/seller/loginSellerAction")
    public ModelAndView loginSellerAction(ModelAndView modelAndView, HttpSession session, String sellerCode,
            String password) {
        Boolean result = sellerService.checkLogin(sellerCode, password);
        System.out.println(result);
        if (result) {
            session.setAttribute("loginedSeller", sellerService.getSeller(sellerCode));
            modelAndView.setViewName("redirect:/seller/salesManagement");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/seller/loginSeller");
        return modelAndView;
    }

    @GetMapping(value = { "/seller/joinSeller", "/seller/joinseller" })
    public ModelAndView joinSeller(ModelAndView modelAndView) {
        modelAndView.setViewName("/seller/joinSeller");
        return modelAndView;
    }

    @PostMapping(value = "/seller/joinSellerAction")
    public ModelAndView joinSellerAction(ModelAndView modelAndView, Seller seller) {
        Boolean result = sellerService.isAlreadyJoined(seller);
        if (result) {
            System.out.println("이미 존재하는 계정이에용");
            modelAndView.setViewName("redirect:/seller/joinSeller");
            return modelAndView;
        }
        sellerService.registerSeller(seller);
        modelAndView.setViewName("redirect:/seller/loginSeller");
        return modelAndView;
    }

    @GetMapping(value = { "/seller/salesManagement", "/seller/salesmanagement" })
    public ModelAndView salesManagement(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Seller seller = (Seller) session.getAttribute("seller");
        List<Product> productList = productService.searchProductBySeller(seller);

        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("/seller/salesManagement");
        return modelAndView;
    }

    @GetMapping(value = { "/seller/salesManagement/registerProduct", "/seller/registerProduct" })
    public ModelAndView registerProduct(ModelAndView modelAndView) {
        modelAndView.setViewName("/seller/registerProduct");
        return modelAndView;
    }

    @PostMapping(value = "/seller/salesManagement/registerProductAction")
    public ModelAndView registerProductAction(@RequestPart MultipartFile files, ModelAndView modelAndView,
            HttpSession session, ProductRequestDto productRequestDto, Integer stockNumber) throws IOException {
        String baseUrl = "/static/uploadFile/" + productRequestDto.getName() + ".jpg";
        productRequestDto.setImageUrl(baseUrl);
        productRequestDto.setSeller((Seller) session.getAttribute("seller"));
        productService.registerProduct(productRequestDto, stockNumber);

        modelAndView.setViewName("redirect:/seller/salesManagement");
        return modelAndView;
    }
}