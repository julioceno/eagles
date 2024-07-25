package com.eagles.api.auth;

import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.auth.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthService authService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCallAuthServiceAndInvokeSignInMethod() {
        SignInDTO signInDTO = new SignInDTO("", "");
        authService.signIn(signInDTO);
        verify(authService).signIn(signInDTO);
    }

    @Test
    void shouldCallAuthServiceAndInvokeRefreshTokenMethod() {
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO("refresh");
        authService.refreshToken(refreshTokenDTO);
        verify(authService).refreshToken(refreshTokenDTO);
    }

    @Test
    void shouldCallAuthServiceAndInvokeLogoutMethod() {
        authService.logout("token");
        verify(authService).logout("token");
    }
}
