package com.jbnu.cocofarm.domain.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jbnu.cocofarm.domain.cart.Cart;
import com.jbnu.cocofarm.domain.customer.CustomerDto.CustomerUpdateDto;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.product.ProductQuestion;
import com.jbnu.cocofarm.domain.product.ProductReview;
import com.jbnu.cocofarm.util.BaseTime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Customer extends BaseTime implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String contact;
    private String postcode;
    private String address;
    private String detailAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<OrderTotal> orderTotalList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<ProductQuestion> productQuestionList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<ProductReview> productReviewList = new ArrayList<>();

    @Builder
    public Customer(String name, String email, String password, String contact, String postcode, String address,
            String detailAddress) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
    }

    public void updateCustomer(CustomerUpdateDto updateDto) {
        this.name = updateDto.getName();
        this.password = updateDto.getPassword();
        this.contact = updateDto.getContact();
        this.postcode = updateDto.getPostcode();
        this.address = updateDto.getAddress();
        this.detailAddress = updateDto.getDetailAddress();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("CUSTOMER"));
        return auth;
    }

    @Override
    public String getUsername() {
        // 아이디 넣어라
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}