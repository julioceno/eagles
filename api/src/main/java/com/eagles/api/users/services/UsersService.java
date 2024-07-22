package com.eagles.api.users.services;


import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private CreateUserService createUserService;

    public UserDTO create(CreateUserDTO userDTO) {
        return createUserService.run(userDTO);
    }
}
