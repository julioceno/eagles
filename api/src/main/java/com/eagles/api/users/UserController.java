package com.eagles.api.users;

import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.CreateUserResponseDTO;
import com.eagles.api.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(@RequestBody CreateUserDTO dto) {
        CreateUserResponseDTO userDTO = usersService.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.user().getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(userDTO);
    }

}
