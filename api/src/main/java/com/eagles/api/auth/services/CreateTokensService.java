package com.eagles.api.auth.services;


import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTokensService {
    final Logger logger = LoggerFactory.getLogger(CreateTokensService.class);

    private final CreateTokenService createTokenService;
    private final CreateRefreshTokenService createRefreshToken;

    public TokensDTO run(User user) {
        logger.info("Generate all tokens....");
        String token = createTokenService.run(user);
        String refreshToken = createRefreshToken.run(user.getId());

        logger.info("Tokens generated");
        return TokensDTO
                .builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
