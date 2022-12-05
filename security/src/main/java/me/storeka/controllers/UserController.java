package me.storeka.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import me.storeka.dto.UserLoginRequest;
import me.storeka.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public String login(@RequestBody UserLoginRequest user, HttpServletResponse response){
        return userService.login(user, response);
    }

}
