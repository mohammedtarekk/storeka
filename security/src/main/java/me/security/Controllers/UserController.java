package me.security.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import me.security.DTO.AuthenticationRequest;
import me.security.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
// @Validated should be added if there are any request param validations
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public void login(@RequestBody @Valid AuthenticationRequest user, HttpServletResponse response) throws IOException {
        userService.login(user, response);
    }

}
