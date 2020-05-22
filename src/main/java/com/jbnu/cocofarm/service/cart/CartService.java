package com.jbnu.cocofarm.service.cart;

import java.util.List;

import com.jbnu.cocofarm.domain.cart.CartDto.CartDisplayDto;
import com.jbnu.cocofarm.domain.cart.CartDto.CartRegisterDto;

public interface CartService {

    void registerCart(CartRegisterDto registerDto, Long customerId);

    List<CartDisplayDto> findByCustomer(Long customerId);
}