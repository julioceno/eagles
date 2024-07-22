package com.eagles.api.auth.services;

import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final SignInService signInService;

    public TokensDTO signIn(SignInDTO signInDTO) {
        return signInService.run(signInDTO);
    }
}
