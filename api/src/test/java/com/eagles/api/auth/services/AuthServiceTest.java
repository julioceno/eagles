package com.eagles.api.auth.services;

import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.SignInDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks
    AuthService authService;

    @Mock
    private SignInService signInService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private LogoutService logoutService;

    @Test
    void shouldCallSignInServiceAndInvokeRunMethod() {
        SignInDTO signInDTO = new SignInDTO("", "");
        authService.signIn(signInDTO);
        verify(signInService).run(signInDTO);
    }

    @Test
    void shouldCallRefreshTokenAndInvokeRunMethod() {
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO("refresh");
        authService.refreshToken(refreshTokenDTO);
        verify(refreshTokenService).run(refreshTokenDTO);
    }

    @Test
    void shouldCallLogoutServiceAndInvokeRunMethod() {
        authService.logout("userId");
        verify(logoutService).run("userId");
    }
}