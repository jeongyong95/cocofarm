package com.jbnu.cocofarm.domain.user;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jbnu.cocofarm.domain.utility.BaseTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orders extends BaseTime {

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    List<OrdersDetail> ordersDetailList = new ArrayList<>();
}