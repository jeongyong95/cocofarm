package com.jbnu.cocofarm.service.customer;

import java.util.List;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerUpdateDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;

public interface CustomerService {

    boolean registerCustomer(CustomerRegisterDto registerDto);

    void updateCustomer(Long customerId, CustomerUpdateDto updateDto);

    void deleteCustomer(Long customerId);

    Customer getCustomer(Long customerId);

    CustomerSessionDto checkCustomer(CustomerLoginDto loginDto);

    boolean checkPassword(Long customerId, String password);

    List<OrderProductDisplayDto> getPurchaseList(CustomerSessionDto sessionDto);

}