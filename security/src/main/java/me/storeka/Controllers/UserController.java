package me.storeka.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import me.storeka.DTO.AuthenticationRequest;
import me.storeka.Services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
// @Validated should be added if there are any request param validations
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public void login(@RequestBody @Valid AuthenticationRequest user, HttpServletResponse response){
        userService.login(user, response);
    }

}
