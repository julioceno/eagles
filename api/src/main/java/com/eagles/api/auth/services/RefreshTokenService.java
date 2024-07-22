package com.eagles.api.auth.services;

import com.eagles.api.auth.domain.RefreshToken;
import com.eagles.api.auth.domain.RefreshTokenRepository;
import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final CreateTokenService createTokenService;
    private final CreateRefreshToken createRefreshToken;

    public TokensDTO run(RefreshTokenDTO refreshTokenDTO) {
        RefreshToken refreshToken = getRefreshToken(refreshTokenDTO.refreshToken());
        throwErrorIfRefreshTokenIsNotValid(refreshToken);

        User user = getUser(refreshToken.getUserId());
        String token = createTokenService.run(user);

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

}
