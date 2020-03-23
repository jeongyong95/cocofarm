package com.jbnu.cocofarm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    @Column(nullable = false, length = 100, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long contact;

    @Column(nullable = false)
    private String adressZipCode;

    @Column(nullable = false)
    private String addressCity;

    @Column(nullable = false)
    private String addressState;

    @Column(nullable = false, updatable = false)
    private Date registeredDate;
}