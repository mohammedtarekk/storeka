package me.security.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.security.Entities.User;
import me.security.Utils.JWTUtils;
import me.security.Config.SecurityConstants;
import me.security.Services.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    private static final Logger log = Logger.getLogger(JWTTokenValidatorFilter.class.getName());
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("Validating JWT Token...");

        String jwtToken = getTokenFromHeader(request.getHeader(SecurityConstants.JWT_HEADER));

        if (jwtToken != null && JWTUtils.validateToken(jwtToken)) {

            User user = (User) userDetailsService.loadUserByUsername(JWTUtils.getTokenSubject(jwtToken));

            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                    user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("JWT Token Validation Success");

        }

        filterChain.doFilter(request, response);
    }

    @Override // excluded endpoints
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/user/login");
    }

    private String getTokenFromHeader(String header) {

        if(header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX))
            return header.replace(SecurityConstants.TOKEN_PREFIX,"");

        return null;
    }

}
