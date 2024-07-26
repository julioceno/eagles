package com.eagles.api.users.services;

import com.eagles.api.users.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @InjectMocks
    UsersService usersService;

    @Mock
    CreateUserService createUserService;

    @Test
    void shouldCallCreateUserServiceAndInvokeRunMethod() {
        CreateUserDTO createUserDTO = new CreateUserDTO("", "", "");
        createUserService.run(createUserDTO);
        verify(createUserService).run(createUserDTO);
    }
}