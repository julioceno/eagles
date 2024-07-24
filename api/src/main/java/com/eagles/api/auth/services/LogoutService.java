package com.eagles.api.auth.services;

import com.eagles.api.auth.domain.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class LogoutService {
    private final Logger logger = LoggerFactory.getLogger(LogoutService.class);

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private DecodedTokenService decodedTokenService;

    public void run(String token) {
        String userId = decodedTokenService.run(token).id();

        logger.info(format("Delete refresh token %s", userId));
        refreshTokenRepository.deleteByUserId(userId);
        logger.info("Refresh token deleted");
    }
}
