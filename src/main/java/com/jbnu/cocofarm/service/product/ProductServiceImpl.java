package com.jbnu.cocofarm.service.product;

import java.util.ArrayList;
import java.util.List;

import com.jbnu.cocofarm.domain.product.Product;
import com.jbnu.cocofarm.domain.product.ProductDetail;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailRegisterDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDetailDto.DetailUpdateDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductDisplayDto;
import com.jbnu.cocofarm.domain.product.dto.ProductDto.ProductRegisterDto;
import com.jbnu.cocofarm.domain.product.repository.ProductDetailRepository;
import com.jbnu.cocofarm.domain.product.repository.ProductRepository;
import com.jbnu.cocofarm.domain.seller.Seller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;
    private ProductDetailRepository detailRepo;

    @Override
    public void registerProduct(ProductRegisterDto registerDto, DetailRegisterDto detailDto) {
        detailRepo.save(detailDto.toEntity(productRepo.save(registerDto.toEntity())));
    }

    @Override
    public List<ProductDisplayDto> findBySeller(Seller seller) {
        List<Product> productList = productRepo.findBySeller(seller);
        List<ProductDisplayDto> displayDtoList = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            ProductDisplayDto displayDto = new ProductDisplayDto(productList.get(i));
            displayDtoList.add(displayDto);
        }
        return displayDtoList;
    }

    @Override
    public List<ProductDisplayDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductDisplayDto> displayDtoList = modelMapper.map(productRepo.findAll(),
                new TypeToken<List<ProductDisplayDto>>() {
                }.getType());
        return displayDtoList;
    }

    @Override
    public ProductDisplayDto findById(Long id) {
        return new ProductDisplayDto(productRepo.findById(id).get());
    }

    @Override
    public void updateStock(int orderedQuantity, Long ProductId) {
        ProductDetail updatedDetail = productRepo.findById(ProductId).get().getProductDetail();
        DetailUpdateDto updateDto = new DetailUpdateDto(updatedDetail);
        updateDto.setStock(updateDto.getStock() - orderedQuantity);
        updatedDetail.updateDetail(updateDto);
    }

}