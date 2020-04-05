package com.jbnu.cocofarm.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jbnu.cocofarm.domain.asisstant.Role;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 100, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column
    private Integer status;

    @Column(nullable = false)
    private Long contact;

    @Column(nullable = true)
    private String adressZipCode;

    @Column(nullable = true)
    private String addressCity;

    @Column(nullable = true)
    private String addressState;

    @CreatedDate
    private LocalDateTime createdTimestamp;

    @LastModifiedDate
    private LocalDateTime updatedTimestamp;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    List<Order> orderList = new ArrayList<>();
}