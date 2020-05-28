package com.jbnu.cocofarm.service.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.customer.Customer;
import com.jbnu.cocofarm.domain.customer.CustomerRepository;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.order.repository.OrderProductRepository;
import com.jbnu.cocofarm.domain.order.repository.OrderTotalRepository;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerLoginDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerRegisterDto;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerSessionDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    private CustomerRepository customerRepo;
    private OrderTotalRepository orderTotalRepo;
    private OrderProductRepository orderProductRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepo.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email + "이라는 계정이 존재하지 않습니다.");
        }
        return customer.get();
    }

    @Override
    public boolean registerCustomer(CustomerRegisterDto registerDto) {
        if (customerRepo.findByEmail(registerDto.getEmail()).isPresent()) {
            return false;
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        customerRepo.save(registerDto.toEntity());
        return true;
    }

    @Override
    public CustomerSessionDto checkCustomer(CustomerLoginDto loginDto) {
        Optional<Customer> customer = customerRepo.findByEmail(loginDto.getEmail());
        if (customer.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(), customer.get().getPassword())) {
                CustomerSessionDto customerSessionDto = new CustomerSessionDto();
                customerSessionDto.setId(customer.get().getId());
                customerSessionDto.setName(customer.get().getName());
                return customerSessionDto;
            }
            System.out.println("비밀번호 오류");
            return null;
        }
        System.out.println("존재하지 않음");
        return null;
    }

    @Override
    public List<OrderProductDisplayDto> getPurchaseList(CustomerSessionDto sessionDto) {

        ModelMapper modelMapper = new ModelMapper();
        Customer customer = customerRepo.findById(sessionDto.getId()).get();
        
        List<OrderTotal> orderTotalList = orderTotalRepo.findByCustomer(customer);
        List<OrderProductDisplayDto> orderProductList = new ArrayList<OrderProductDisplayDto>();

        for (int i = 0; i < orderTotalList.size(); i++) {
            List<OrderProductDisplayDto> orderProduct = modelMapper.map(
                    orderProductRepo.findByOrderTotal(orderTotalList.get(i)),
                    new TypeToken<List<OrderProductDisplayDto>>() {
                    }.getType());

            orderProductList.addAll(orderProduct);
        }
        return orderProductList;
    }
}