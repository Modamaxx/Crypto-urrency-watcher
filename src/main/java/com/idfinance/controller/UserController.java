package com.idfinance.controller;

import com.idfinance.dto.user.UserRequest;
import com.idfinance.dto.user.UserResponse;
import com.idfinance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/notify")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse notify(@RequestBody @Valid @NotNull UserRequest user) {
        return userService.save(user);
    }
}
