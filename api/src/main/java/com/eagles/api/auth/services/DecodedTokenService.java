package com.eagles.api.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eagles.api.auth.dto.SubjectDTO;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DecodedTokenService {
    private final Logger logger = LoggerFactory.getLogger(DecodedTokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public SubjectDTO run(String token) {
        logger.info("Decoding token...");
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String decodedJWT = JWT.require(algorithm)
                .withIssuer("eagles-api")
                .build()
                .verify(token)
                .getSubject();

            ObjectMapper objectMapper = new ObjectMapper();
            SubjectDTO subjectJson = objectMapper.readValue(decodedJWT, SubjectDTO.class);

            logger.info("Decoded token");
            return subjectJson;
        } catch (JsonProcessingException e) {
            throw new UnauthorizedException();
        }
    }
}
