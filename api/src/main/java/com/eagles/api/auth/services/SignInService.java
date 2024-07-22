package com.eagles.api.auth.services;


import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {
    final Logger logger = LoggerFactory.getLogger(SignInService.class);
    final String errorDefault = "Credenciais inválidas";

    private final AuthenticationManager authenticationManager;
    private final CreateTokenService createTokenService;
    private final CreateRefreshToken createRefreshToken;

    public TokensDTO run(SignInDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();

        String token = createTokenService.run(user);
        String refreshToken = createRefreshToken.run(user.getId());
        return TokensDTO
                .builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }
}
