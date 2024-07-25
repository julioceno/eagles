package com.eagles.api.users.services;

import com.eagles.api.auth.services.CreateTokensService;
import com.eagles.api.infra.http.exceptions.BadRequestException;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.domain.UserRepository;
import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.CreateUserResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static java.lang.String.format;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreateUserServiceTest {
    @InjectMocks
    CreateUserService createUserService;

    @Mock
    UserRepository userRepository;

    @Mock
    CreateTokensService createTokensService;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    User user;
    CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        user = new User("id", "name", "email", "password", null, null);
        createUserDTO = new CreateUserDTO(user.getName(), user.getEmail(), user.getPassword());
        passwordEncoder = new BCryptPasswordEncoder();

        when(userRepository.save(any(User.class))).thenReturn(user);

    }

    @Test
    void shouldCallUserRepositoryAndInvokeFindByEmailMethod() {
        createUserService.run(createUserDTO);
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    void shouldThrowErrorIfUserAlreadyExists() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            createUserService.run(createUserDTO);
        });

        assertEquals(format("Usuário de email %s já existe.", createUserDTO.email()), exception.getMessage());
    }

    @Test
    void shouldCallPasswordEncoderAndInvokeEncodeMethod() {
        createUserService.run(createUserDTO);

        verify(userRepository).save(any(User.class));
        verify(userRepository).save(argThat(newUser -> {
            return passwordEncoder.matches("password", newUser.getPassword());
        }));
    }

    @Test
    void shouldCallCreateTokensServiceAndInvokeRunMethod() {
        createUserService.run(createUserDTO);
        verify(createTokensService).run(user);
    }

    @Test
    void shouldReturnUserInPatternDTO() {
        CreateUserResponseDTO response = createUserService.run(createUserDTO);
        assertThat(response).isInstanceOf(CreateUserResponseDTO.class);
    }
}