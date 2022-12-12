package me.storeka.Services;

import jakarta.servlet.http.HttpServletResponse;
import me.storeka.Utils.JWTUtils;
import me.storeka.SpringSecurity.SecurityConstants;
import me.storeka.DTO.AuthenticationRequest;
import me.storeka.Repositories.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public record UserService(UserRepo userRepo, AuthenticationManager authenticationManager) {

    public void login(AuthenticationRequest user, HttpServletResponse response) throws IOException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String JwtToken = JWTUtils.generateToken(user.getUsername(),
                Map.of("roles",
                        authentication.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
        );

        response.setHeader(SecurityConstants.JWT_HEADER, JwtToken);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"User logged in successfully.\"}");

    }

}
