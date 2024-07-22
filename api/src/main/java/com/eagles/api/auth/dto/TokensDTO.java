package com.eagles.api.auth.dto;

import lombok.Builder;

@Builder
public record TokensDTO(String token, String refreshToken) {
}
