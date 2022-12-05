package me.storeka.dto;

import java.util.Objects;

public record UserLoginRequest(String username, String password) {

    public UserLoginRequest{
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
    }

}
