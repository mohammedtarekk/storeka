package me.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull(message = "Username must not be null")
    @NotBlank(message = "Username must not be blank")
    String username;

    @NotNull(message = "Password must not be null")
    @NotBlank(message = "Password must not be blank")
    String password;

}
