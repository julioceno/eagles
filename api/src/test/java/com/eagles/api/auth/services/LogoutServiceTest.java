package com.eagles.api.auth.services;

import com.eagles.api.auth.domain.RefreshTokenRepository;
import com.eagles.api.auth.dto.SubjectDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LogoutServiceTest {
    @InjectMocks
    LogoutService logoutService;

    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @Mock
    DecodedTokenService decodedTokenService;

    @BeforeEach
    void setUp() {
        SubjectDTO subjectDTO = new SubjectDTO("id", "");
        when(decodedTokenService.run(anyString())).thenReturn(subjectDTO);
    }

    @Test
    void shouldCallDecodedTokenServiceAndCallRunMethod() {
        logoutService.run("token");
        verify(decodedTokenService).run("token");
    }

    @Test
    void shouldCallRefreshTokenRepositoryAndInvokeDeleteByUserId() {
        logoutService.run("token");
        verify(refreshTokenRepository).deleteByUserId("id");
    }
}