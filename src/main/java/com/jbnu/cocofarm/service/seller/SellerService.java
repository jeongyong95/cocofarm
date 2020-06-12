package com.jbnu.cocofarm.service.seller;

import java.util.List;

import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerLoginDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;

public interface SellerService {

    boolean registerSeller(SellerRegisterDto registerDto);

    Seller getSeller(Long id);

    SellerSessionDto checkSeller(SellerLoginDto loginDto);

    List<OrderProductDisplayDto> getSaleList(SellerSessionDto sellerSessionDto);

}