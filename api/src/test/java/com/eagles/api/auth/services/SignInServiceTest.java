package com.eagles.api.auth.services;

import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignInServiceTest {
    @InjectMocks
    SignInService signInService;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    CreateTokensService createTokensService;

    SignInDTO signInDTO = new SignInDTO("", "");

    @BeforeEach
    void setUp() {
        User user = new User();
        Authentication auth = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        when(createTokensService.run(user)).thenReturn(new TokensDTO("accessToken", "refreshToken"));
    }

    @Test
    void shouldCallAuthenticationManagerAndInvokeAuthenticate() {
        signInService.run(signInDTO);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void shouldCallCreateTokensServiceAndInvokeRunMethod() {
        signInService.run(signInDTO);
        verify(createTokensService).run(any(User.class));
    }
}