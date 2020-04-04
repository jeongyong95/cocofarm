package com.jbnu.cocofarm.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기본 CRUD 이외에 필요한 query를 추가합니다. querydsl 참고

    Optional<User> findByEmail(String email);
}