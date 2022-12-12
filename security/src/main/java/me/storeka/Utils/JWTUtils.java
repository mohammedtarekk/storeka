package me.storeka.Utils;

import io.jsonwebtoken.*;
import me.storeka.SpringSecurity.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    public static String generateToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(SecurityConstants.VALID_HOURS)))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .setClaims(claims)
                .compact();

    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public static String getTokenSubject(String token) {
        return Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody().getSubject();
    }

}
