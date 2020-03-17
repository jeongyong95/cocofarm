package com.jbnu.cocofarm.domain;

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
    @Column(nullable = true, length = 100)
    private String userId;

    @Column(nullable = true, length = 50)
    private String userPw;
}