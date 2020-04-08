package com.jbnu.cocofarm.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.jbnu.cocofarm.domain.product.OrdersDetail;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String receiverName;

    @Column
    private Integer receiverContact;

    // 주소는 주소 api 사용에 따라 컬럼 설정
    @Column
    private String receiverAddress;

    @Column
    private String receiverMessage;

    @Column
    private Integer payment;

    @Column
    private String payMethod;

    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    List<OrdersDetail> ordersDetailList = new ArrayList<>();
}