package com.idfinance.service;

import com.idfinance.dto.user.CreateUserRequest;
import com.idfinance.dto.user.CreateUserResponse;
import com.idfinance.model.User;
import com.idfinance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse save(CreateUserRequest userDto) {
        User user = userDto.toUser();
        userRepository.save(user);
        return CreateUserResponse.from(user);
    }
}
