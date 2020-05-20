package com.jbnu.cocofarm.service.customer;

import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;

public interface CustomerService {

    boolean registerCustomer(CustomerRegisterDto registerDto);

    CustomerSessionDto checkCustomer(CustomerLoginDto loginDto);

}