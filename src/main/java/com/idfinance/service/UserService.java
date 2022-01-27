package com.idfinance.service;

import com.idfinance.dto.user.UserRequest;
import com.idfinance.dto.user.UserResponse;
import com.idfinance.exception.ExpectedException;
import com.idfinance.model.Coin;
import com.idfinance.model.User;
import com.idfinance.repository.CoinRepository;
import com.idfinance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CoinRepository coinRepository;

    public UserResponse save(UserRequest userDto) {
        Coin coin = coinRepository.findBySymbol(userDto.getSymbol())
                .orElseThrow(() -> new ExpectedException("could not get Coin by symbol: " + userDto.getSymbol()));
        User user = userDto.toUser(coin);
        userRepository.save(user);
        return UserResponse.from(user);
    }
}
