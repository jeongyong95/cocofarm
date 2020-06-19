package com.jbnu.cocofarm.service.order;

import java.util.List;

import com.jbnu.cocofarm.domain.customer.CustomerRepository;
import com.jbnu.cocofarm.domain.order.OrderProduct;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalDisplayDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;
import com.jbnu.cocofarm.domain.order.repository.OrderProductRepository;
import com.jbnu.cocofarm.domain.order.repository.OrderTotalRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private CustomerRepository customerRepo;
    private ProductRepository productRepo;
    private OrderProductRepository orderProductRepo;
    private OrderTotalRepository orderTotalRepo;

    @Override
    public void registerOrder(OrderTotalRegisterDto totalRegisterDto,
            List<OrderProductRegisterDto> productRegisterDtoList, Long CustomerId) {
        totalRegisterDto.setCustomer(customerRepo.getOne(CustomerId));

        OrderTotal orderTotal = orderTotalRepo.save(totalRegisterDto.toEntity());

        for (int i = 0; i < productRegisterDtoList.size(); i++) {
            productRegisterDtoList.get(i).setOrderTotal(orderTotal);
            productRegisterDtoList.get(i).setProduct(productRepo.getOne(productRegisterDtoList.get(i).getProductId()));
            orderProductRepo.save(productRegisterDtoList.get(i).toEntity());
        }

    }

    @Override
    public OrderProduct getOrderProduct(Long orderProductId) {
        return orderProductRepo.getOne(orderProductId);
    }

    @Override
    public OrderTotal getOrderTotal(Long orderTotalId) {
        return orderTotalRepo.getOne(orderTotalId);
    }

    @Override
    public void cancelOrder(Long id) {

    }

    @Override
    public OrderProductDisplayDto findByOrderProductId(Long orderProductId) {
        return new OrderProductDisplayDto(orderProductRepo.findById(orderProductId).get());
    }

    

}