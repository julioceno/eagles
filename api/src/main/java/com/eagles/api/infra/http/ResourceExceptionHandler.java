package com.eagles.api.infra.http;

import com.eagles.api.infra.http.exceptions.BadRequestException;
import com.eagles.api.infra.http.exceptions.NotFoundException;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(
            NotFoundException e,
            HttpServletRequest request
    ) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<StandardError> unauthorized(
            UnauthorizedException e,
            HttpServletRequest request
    ) {
        String error = "Unauthorized";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest(
            BadRequestException e,
            HttpServletRequest request
    ) {
        String error = "Bad Request";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> internalServerError(
            RuntimeException e,
            HttpServletRequest request
    ) {
        e.printStackTrace();
        String error = "Internal Server Error";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Ocorreu um erro inesperado, entre em contato com o suporte.";
        StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}