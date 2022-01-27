package com.idfinance.dto.user;

import com.idfinance.model.Coin;
import com.idfinance.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String symbol;

    public User toUser(Coin coin) {
        return User.builder()
                .username(username)
                .coin(coin)
                .price(coin.getActualPrice())
                .build();
    }
}
