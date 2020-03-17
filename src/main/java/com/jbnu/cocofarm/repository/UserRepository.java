package com.jbnu.cocofarm.repository;

import com.jbnu.cocofarm.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}