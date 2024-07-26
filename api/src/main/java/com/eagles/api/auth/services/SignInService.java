package com.eagles.api.auth.services;


import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {
    final Logger logger = LoggerFactory.getLogger(SignInService.class);

    private final AuthenticationManager authenticationManager;
    private final CreateTokensService createTokensService;

    public TokensDTO run(SignInDTO dto) {
        logger.info("Verifying if credentials are valid");
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();

        logger.info("Credentials are valid");
        return createTokensService.run(user);
    }
}
