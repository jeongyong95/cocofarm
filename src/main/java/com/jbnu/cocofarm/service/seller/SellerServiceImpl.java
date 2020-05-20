package com.jbnu.cocofarm.service.seller;

import java.util.Optional;

import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerRepository;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerLoginDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean registerSeller(SellerRegisterDto registerDto) {
        Optional<Seller> seller = sellerRepo.findBySellerCode(registerDto.getSellerCode());
        if (seller.isPresent()) {
            return false;
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        sellerRepo.save(registerDto.toEntity());
        return true;
    }

    @Override
    public SellerSessionDto checkSeller(SellerLoginDto loginDto) {
        Optional<Seller> seller = sellerRepo.findBySellerCode(loginDto.getSellerCode());
        if (seller.isPresent()) {
            if (passwordEncoder.matches(loginDto.getPassword(), seller.get().getPassword())) {
                SellerSessionDto sessionDto = new SellerSessionDto();
                sessionDto.setId(seller.get().getId());
                sessionDto.setName(seller.get().getName());
                return sessionDto;
            }
            System.out.println("비밀번호 오류");
            return null;
        }
        System.out.println("존재하지 않는 계정");
        return null;
    }

    @Override
    public Seller getSeller(Long id) {
        return sellerRepo.findById(id).get();
    }

}