package com.eagles.api.auth.services;

import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final SignInService signInService;
    private final RefreshTokenService refreshTokenService;

    public TokensDTO signIn(SignInDTO signInDTO) {
        return signInService.run(signInDTO);
    }

    public TokensDTO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        return refreshTokenService.run(refreshTokenDTO);
    }
}
