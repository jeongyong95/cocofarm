package com.jbnu.cocofarm.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.service.product.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class ProductController {

    private ProductService productService;

    @GetMapping(value = { "/salesManagement", "/salesmanagement" })
    public ModelAndView salesManagement(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Seller loginedSeller = (Seller) session.getAttribute("loginedSeller");
        List<Product> productList = productService.searchProductBySeller(loginedSeller);

        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("salesManagement");
        return modelAndView;
    }

    @GetMapping(value = { "/salesManagement/registerProduct", "/registerProduct" })
    public ModelAndView registerProduct(ModelAndView modelAndView) {
        modelAndView.setViewName("registerProduct");
        return modelAndView;
    }

    @PostMapping(value = { "/salesManagement/registerProductAction", "/registerProductAction" })
    public ModelAndView registerProductAction(@RequestPart MultipartFile files, ModelAndView modelAndView,
            HttpSession session, Product product, Integer stockNumber) throws IOException {
        String baseUrl = "/static/uploadFile/" + product.getName() + ".jpg";
        // files.transferTo(new File("/Users/jeongyong/Downloads/cocofarm-Jung
        // (2)/src/main/resources" + baseUrl));
        product.setImageUrl(baseUrl);
        product.setSeller((Seller) session.getAttribute("loginedSeller"));
        productService.registerProduct(product, stockNumber);

        modelAndView.setViewName("redirect:/salesManagement");
        return modelAndView;
    }

}