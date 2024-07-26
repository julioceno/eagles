package com.eagles.api.auth.services;

import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CreateTokensServiceTest {
    @InjectMocks
    CreateTokensService createTokensService;

    @Mock
    CreateTokenService createTokenService;

    @Mock
    CreateRefreshTokenService createRefreshTokenService;

    User user = new User("id", null, null, null, null, null);

    @Test
    void shouldCallCreateTokenServiceAndInvokeRunMethod() {
        createTokensService.run(user);
        verify(createTokenService).run(user);
    }

    @Test
    void shouldCallCreateRefreshTokenServiceAndInvokeRunMethod() {
        createTokensService.run(user);
        verify(createRefreshTokenService).run(user.getId());
    }

    @Test
    void shouldRetunDataWithPatternTokensDTO() {
        TokensDTO tokensDTO =  createTokensService.run(user);
        assertThat(tokensDTO).isInstanceOf(TokensDTO.class);
    }
}