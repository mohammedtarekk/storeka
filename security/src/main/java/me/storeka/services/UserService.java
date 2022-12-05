package me.storeka.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.storeka.config.SecurityConstants;
import me.storeka.dto.UserLoginRequest;
import me.storeka.repos.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public record UserService(UserRepo userRepo) {

    public String login(UserLoginRequest user, HttpServletResponse response) {

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.username(), user.password()));

//        if (null != authentication) {
//            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
//            String jwt = Jwts.builder().setIssuer("STOREKA").setSubject("JWT Token")
//                    .claim("username", authentication.getName())
//                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date((new Date()).getTime() + SecurityConstants.EXPIRATION_TIME))
//                    .signWith(key).compact();
//            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
//        }

        return "ss";
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
