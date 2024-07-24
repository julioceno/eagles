package com.eagles.api.auth.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.eagles.api.auth.dto.SubjectDTO;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import com.eagles.api.users.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class CreateTokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiresIn}")
    private Long accessTokenExpiresIn;

    public String run(User user){
        SubjectDTO subjectDTO = new SubjectDTO(user.getId(), user.getEmail());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String subjectJson = objectMapper.writeValueAsString(subjectDTO);

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("eagles-api")
                    .withSubject(subjectJson)
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException | JsonProcessingException exception){
            throw new UnauthorizedException("Não é possível autenticar o usuário.");
        }
    }

    private Instant generateExpirationDate(){
        return Instant
                .now()
                .plus(Duration.ofMillis(accessTokenExpiresIn));
    }

}