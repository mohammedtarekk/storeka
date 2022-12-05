package me.storeka.config;

public interface SecurityConstants {

    String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    String JWT_HEADER = "Authorization";
    Integer EXPIRATION_TIME = 8 * 60 * 60 * 1000; // 8 hours
    String TOKEN_TYPE = "Bearer";
    String TOKEN_PREFIX = "Bearer ";
}
