package com.jbnu.cocofarm.service.customer;

import java.util.List;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;

public interface CustomerService {

    boolean registerCustomer(CustomerRegisterDto registerDto);

    CustomerSessionDto checkCustomer(CustomerLoginDto loginDto);

    List<OrderProductDisplayDto> getPurchaseList(CustomerSessionDto sessionDto);

}