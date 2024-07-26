package com.eagles.api.auth.services;

import com.eagles.api.auth.domain.RefreshToken;
import com.eagles.api.auth.domain.RefreshTokenRepository;
import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

@Service
public class RefreshTokenService {
    private final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    @Value("${api.security.refreshToken.refreshIn}")
    private Long refreshTokenRefreshIn;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateTokenService createTokenService;

    @Autowired
    private CreateRefreshTokenService createRefreshTokenService;

    public TokensDTO run(RefreshTokenDTO refreshTokenDTO) {
        RefreshToken refreshToken = getRefreshToken(refreshTokenDTO.refreshToken());
        throwErrorIfRefreshTokenIsNotValid(refreshToken);

        User user = getUser(refreshToken.getUserId());
        String token = createTokenService.run(user);

        boolean canUpdateRefreshToken = this.canUpdateRefreshToken(refreshToken);
        if (canUpdateRefreshToken) {
            logger.info("Updating refresh token and return...");
            String refreshTokenUpdated = createRefreshTokenService.run(user.getId());
            return new TokensDTO(token, refreshTokenUpdated);
        }

        logger.info("Return only access token");
        return new TokensDTO(token, null);
    }

    private RefreshToken getRefreshToken(String refreshTokenId) {
        logger.info(format("Getting refresh token with id %s", refreshTokenId));
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenId).orElseThrow(() -> {
            logger.error(format("Token not found"));
            throw new UnauthorizedException();
        });

        logger.info("Token found");
        return refreshToken;
    }

    private void throwErrorIfRefreshTokenIsNotValid(RefreshToken refreshToken) {
        Instant now = new Date().toInstant();
        boolean refreshTokenIsValid = refreshToken.getExpiresIn().isAfter(now);

        if (!refreshTokenIsValid) {
            logger.error(format("Token %s is not valid", refreshToken.getId()));
            throw new UnauthorizedException();
        }

        logger.info(format("Token %s is valid", refreshToken.getId()));
    }

    private User getUser(String userId) {
        logger.info(format("Getting user with id %s", userId));
        User user = userRepository.findById(userId).orElseThrow(() -> {
            logger.error(format("User not found"));
            throw new UnauthorizedException();
        });

        logger.info("User found");
        return user;
    }

    private boolean canUpdateRefreshToken(RefreshToken refreshToken) {
        Instant createdAtInstant = refreshToken.getCreatedAt().toInstant();
        Instant now = new Date().toInstant();

        long hoursTokenCreated = Duration.between(createdAtInstant, now).toHours();
        long hoursToExpires = TimeUnit.MILLISECONDS.toHours(refreshTokenRefreshIn);

        return hoursTokenCreated > hoursToExpires;
    }
}
