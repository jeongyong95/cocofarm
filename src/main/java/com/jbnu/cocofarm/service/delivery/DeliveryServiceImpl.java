package com.jbnu.cocofarm.service.delivery;

import com.jbnu.cocofarm.domain.delivery.DeliveryRepository;
import com.jbnu.cocofarm.domain.delivery.DeliveryDto.DeliveryRegisterDto;
import com.jbnu.cocofarm.domain.order.repository.OrderProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepo;
    private OrderProductRepository orderProductRepo;

    @Override
    public void registerDelivery(DeliveryRegisterDto registerDto, Long orderProductId) {
        registerDto.setOrderProduct(orderProductRepo.findById(orderProductId).get());
        deliveryRepo.save(registerDto.toEntity());
    }

}