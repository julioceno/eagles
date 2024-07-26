package com.eagles.api.users;

import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.CreateUserResponseDTO;
import com.eagles.api.users.dto.UserDTO;
import com.eagles.api.users.services.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UsersService usersService;

    CreateUserDTO createUserDTO = new CreateUserDTO("name", "email", "password");
    UserDTO userDTO = new UserDTO("name", "email", "password");
    TokensDTO tokensDTO = new TokensDTO("", "");
    CreateUserResponseDTO createUserResponseDTO = new CreateUserResponseDTO(userDTO, tokensDTO);

    @Test
    void shouldCallUsersServiceAndInvokeCreateMethod() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(usersService.create(any(CreateUserDTO.class))).thenReturn(createUserResponseDTO);
        userController.create(createUserDTO);

        verify(usersService).create(any(CreateUserDTO.class));
    }
}