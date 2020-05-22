package com.jbnu.cocofarm.service.cart;

import java.util.ArrayList;
import java.util.List;

import com.jbnu.cocofarm.domain.cart.CartDto.CartDisplayDto;
import com.jbnu.cocofarm.domain.cart.CartDto.CartRegisterDto;
import com.jbnu.cocofarm.domain.cart.Cart;
import com.jbnu.cocofarm.domain.cart.CartRepository;
import com.jbnu.cocofarm.domain.customer.CustomerRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepo;
    private CustomerRepository customerRepo;
    private ProductRepository productRepo;

    @Override
    public void registerCart(CartRegisterDto registerDto, Long customerId) {
        registerDto.setCustomer(customerRepo.getOne(customerId));
        registerDto.setProductDetail(productRepo.getOne(registerDto.getProductId()).getProductDetail());
        cartRepo.save(registerDto.toEntity());
    }

    @Override
    public List<CartDisplayDto> findByCustomer(Long customerId) {
        List<CartDisplayDto> cartDisplayDtoList = new ArrayList<>();
        List<Cart> customerCartList = cartRepo.findByCustomer(customerRepo.getOne(customerId));

        for (int i = 0; i < customerCartList.size(); i++) {
            CartDisplayDto displayDto = new CartDisplayDto();
            displayDto.setId(customerCartList.get(i).getId());
            displayDto.setPrice(customerCartList.get(i).getProductDetail().getProduct().getPrice());
            displayDto.setProductName(customerCartList.get(i).getProductDetail().getProduct().getName());
            displayDto.setQuantity(customerCartList.get(i).getQuantity());
            displayDto.setProductTotalPrice(displayDto.getQuantity() * displayDto.getPrice());
            cartDisplayDtoList.add(displayDto);
        }

        return cartDisplayDtoList;
    }
}