package com.eagles.api.auth.services;

import com.eagles.api.auth.domain.RefreshToken;
import com.eagles.api.auth.domain.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

import static java.lang.String.format;

@Service
public class CreateRefreshTokenService {
    private final Logger logger = LoggerFactory.getLogger(CreateRefreshTokenService.class);

    @Value("${api.security.refreshToken.expiresIn}")
    private Long refreshTokenExpiresIn;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String run(String userId) {
        deleteOldRefreshTokenIfExists(userId);
        RefreshToken refreshTokenBuilt = buildRefreshToken(userId);
        RefreshToken createRefreshToken = createRefreshToken(refreshTokenBuilt);

        return createRefreshToken.getId();
    }

    private void deleteOldRefreshTokenIfExists(String userId) {
        logger.info(format("Deleting all refresh tokens from userId %s...", userId));
        int value = refreshTokenRepository.deleteByUserId(userId);

        if (value != 0) {
            logger.info("Token deleted");
        }
    }

    private RefreshToken buildRefreshToken(String userId) {
        Instant expiresIn = generateExpirationDate();
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(userId);
        refreshToken.setExpiresIn(expiresIn);

        return refreshToken;
    }

    private Instant generateExpirationDate(){
        return Instant
                .now()
                .plus(Duration.ofMillis(refreshTokenExpiresIn));
    }

    private RefreshToken createRefreshToken(RefreshToken refreshToken) {
        logger.info(format("Creating refresh token from user with userId %s...", refreshToken.getUserId()));
        RefreshToken refreshTokenCreated = refreshTokenRepository.save(refreshToken);
        logger.info("Refresh token created");

        return refreshTokenCreated;
    }

}
