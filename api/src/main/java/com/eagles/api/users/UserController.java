package com.eagles.api.users;

import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.UserDTO;
import com.eagles.api.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("foi");
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody CreateUserDTO dto) {
        UserDTO userDTO = usersService.create(dto);
        return ResponseEntity.ok(userDTO);
    }

}
