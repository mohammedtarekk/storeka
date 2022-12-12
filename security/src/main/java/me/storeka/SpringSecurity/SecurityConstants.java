package me.storeka.SpringSecurity;

import org.springframework.beans.factory.annotation.Value;

public interface SecurityConstants {

    String SECRET = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    String JWT_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    int VALID_HOURS = 8;
    String HEADER_STRING = "Authorization";
    @Value("${spring.application.name}:" + "${server.port}")
    String TOKEN_ISSUER = "s";
}
