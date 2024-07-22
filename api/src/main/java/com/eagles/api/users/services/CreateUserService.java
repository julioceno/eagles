package com.eagles.api.users.services;

import com.eagles.api.infra.http.exceptions.BadRequestException;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.domain.UserRepository;
import com.eagles.api.users.dto.CreateUserDTO;
import com.eagles.api.users.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.*;

@Service
public class CreateUserService {
    private final Logger logger = LoggerFactory.getLogger(CreateUserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserDTO run(CreateUserDTO createUserDTO) {
        verifyIfUserAlreadyExists(createUserDTO.email());
        User userBuilt = buildUser(createUserDTO);
        User createdUser = createUser(userBuilt);
        return new UserDTO(createdUser);
    }

    private void verifyIfUserAlreadyExists(String email) {
        logger.info(format("Searching user with email %s...", email));
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            logger.error(format("User with email &%s found", email));
            throw new BadRequestException(format("Usuário de email %s já existe.", email));
        }
        logger.info(format("User with email &%s not found", email));
    }

    private User buildUser(CreateUserDTO dto) {
        String encyptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(encyptedPassword);

        return user;
    }

    private User createUser(User user) {
        logger.info("Creating user...");
        User createdUser = userRepository.save(user);
        logger.info("Created user");
        return createdUser;
    }
}
