package com.jbnu.cocofarm.service.seller;

import java.util.Optional;

import javax.transaction.Transactional;

import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class SellerServiceImpl implements SellerService {

    private SellerRepository repo;

    @Override
    public void registerSeller(Seller seller) {
        repo.save(seller);
        System.out.println("판매자로 등록되었습니다.");
    }

    @Override
    public void updateSeller(Seller seller) {
        repo.save(seller);
        System.out.println("판매자 정보가 수정되었습니다.");
    }

    @Override
    public void deleteSeller(Long id) {
        Optional<Seller> findedSeller = repo.findById(id);
        findedSeller.ifPresent(target -> {
            repo.delete(target);
            System.out.println("판매자 탈퇴 성공");
        });
    }

    @Override
    public Seller getSeller(String sellerCode) {
        Optional<Seller> findedSeller = repo.findBySellerCode(sellerCode);
        return findedSeller.get();
    }

    @Override
    public Boolean checkLogin(String sellerCode, String password) {
        Optional<Seller> findedSeller = repo.findBySellerCode(sellerCode);
        Seller seller = new Seller();
        if (findedSeller.isPresent()) {
            seller = findedSeller.get();
            if (!password.equals(seller.getPassword())) {
                System.out.println("비밀번호가 다르당");
                return false;
            }
            System.out.println("로그인 성공");
            return true;
        }
        System.out.println("존재하지 않는 계정입니다.");
        return false;
    }

    @Override
    public Boolean isAlreadyJoined(Seller seller) {
        Optional<Seller> joiner = repo.findBySellerCode(seller.getSellerCode());
        if (joiner.isPresent()) {
            System.out.println("이미 가입한 사업자입니다.");
            return true;
        }
        return false;
    }

}