package com.eagles.api.auth;


import com.eagles.api.auth.dto.RefreshTokenDTO;
import com.eagles.api.auth.dto.SignInDTO;
import com.eagles.api.auth.dto.TokensDTO;
import com.eagles.api.auth.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<TokensDTO> signIn(@RequestBody SignInDTO signInDTO) {
        TokensDTO tokensDTO = authService.signIn(signInDTO);
        return ResponseEntity.ok(tokensDTO);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokensDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        TokensDTO tokensDTO = authService.refreshToken(refreshTokenDTO);
        return ResponseEntity.ok(tokensDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestAttribute("authorization") String token) {
        authService.logout(token);
        return ResponseEntity.noContent().build();
    }
}
