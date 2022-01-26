package com.idfinance.controller;

import com.idfinance.dto.user.CreateUserRequest;
import com.idfinance.dto.user.CreateUserResponse;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/notify")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse notify(@RequestBody CreateUserRequest user) {
        return userService.save(user);
    }
}
