package com.jbnu.cocofarm.controller.product;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.review.Review;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.service.basket.BasketService;
import com.jbnu.cocofarm.service.orders.OrdersTempService;
import com.jbnu.cocofarm.service.product.ProductService;
import com.jbnu.cocofarm.service.review.ReviewService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;
    private ReviewService reviewService;
    private BasketService basketService;
    private OrdersTempService ordersTempService;

    @GetMapping(value = { "/", "/index" })
    public ModelAndView index(ModelAndView modelAndView) {
        List<Product> productList = productService.getAllProducts();

        modelAndView.addObject("template", "fragments/content/main");
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("/product/index");
        return modelAndView;
    }

    @GetMapping(value = { "/about" })
    public ModelAndView about(ModelAndView modelAndView) {
        modelAndView.addObject("content", "fragments/content/aboutDetail");

        modelAndView.setViewName("/product/index");
        return modelAndView;
    }

    @GetMapping(value = "product/searchProduct")
    public ModelAndView searchProductByKeyword(String searchKeyword) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.searchProductByName(searchKeyword);

        modelAndView.addObject("searchKeyword", searchKeyword);
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("/product/searchProduct");
        return modelAndView;
    }

    @GetMapping(value = "product/productDetailView/{productId}")
    public ModelAndView productDetailView(@PathVariable Long productId) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.searchProductById(productId);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("/product/productDetail");
        return modelAndView;
    }

    @PostMapping(value = "product/productDetailView/registerReviewAction")
    public ModelAndView registerReviewAction(HttpSession session, Review review, Long productId) {
        ModelAndView modelAndView = new ModelAndView();
        review.setProduct(productService.searchProductById(productId));
        review.setUser((User) session.getAttribute("user"));
        reviewService.registerReview(review);
        modelAndView.setViewName("redirect:/product/productDetailView/" + productId);
        return modelAndView;
    }

    @PostMapping(value = "product/productDetailView/insertIntoBasket")
    public ModelAndView insertIntoBasket(HttpSession session, Basket basket, Long productDetailId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        ProductDetail productDetail = productService.searchProductDetailById(productDetailId);
        basket.setProductDetail(productDetail);
        basket.setUser(user);

        basketService.registerBasket(basket);
        modelAndView.setViewName("redirect:/user/basket");
        return modelAndView;
    }

    @PostMapping(value = "product/productDetailView/buyProduct")
    public ModelAndView buyProduct(HttpSession session, OrdersTemp ordersTemp, Long productDetailId) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user");

        ProductDetail productDetail = productService.searchProductDetailById(productDetailId);
        ordersTemp.setProductDetail(productDetail);
        ordersTemp.setUser(user);

        ordersTempService.registerOrdersTemp(ordersTemp);
        modelAndView.setViewName("redirect:/user/orders");
        return modelAndView;
    }
}