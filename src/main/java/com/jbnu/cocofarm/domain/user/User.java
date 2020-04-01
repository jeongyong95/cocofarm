package com.jbnu.cocofarm.domain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.jbnu.cocofarm.domain.asisstant.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 100, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long contact;

    @Column(nullable = true)
    private String adressZipCode;

    @Column(nullable = true)
    private String addressCity;

    @Column(nullable = true)
    private String addressState;

    @Column(nullable = false, updatable = false)
    private Date registeredDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}