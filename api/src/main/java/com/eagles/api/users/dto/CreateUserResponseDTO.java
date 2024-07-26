package com.eagles.api.users.dto;

import com.eagles.api.auth.dto.TokensDTO;

public record CreateUserResponseDTO(UserDTO user, TokensDTO tokens) {}