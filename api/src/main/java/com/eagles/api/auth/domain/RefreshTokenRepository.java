package com.eagles.api.auth.domain;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    @Modifying
    @Transactional
    int deleteByUserId(String userId);
}
