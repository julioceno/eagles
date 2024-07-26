package com.eagles.api.infra;

import com.eagles.api.auth.dto.SubjectDTO;
import com.eagles.api.auth.services.DecodedTokenService;
import com.eagles.api.infra.http.exceptions.UnauthorizedException;
import com.eagles.api.users.domain.User;
import com.eagles.api.users.domain.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private DecodedTokenService decodedTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = getLoginUserByToken(token);

        if (login != null) {
            Optional<User> user = userRepository.findByEmail(login);
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

    private String getLoginUserByToken(String token) {
        try {
            SubjectDTO subject = decodedTokenService.run(token);
            return subject.email();
        } catch (UnauthorizedException error) {
            return null;
        }
    }
}
