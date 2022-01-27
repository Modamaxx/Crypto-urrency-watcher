package com.idfinance.dto.user;

import com.idfinance.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private Long coinId;
    private String symbol;
    private Double price;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .coinId(user.getCoin().getId())
                .symbol(user.getCoin().getSymbol())
                .price(user.getPrice())
                .build();
    }
}
