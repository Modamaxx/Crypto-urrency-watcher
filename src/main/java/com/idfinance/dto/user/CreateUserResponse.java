package com.idfinance.dto.user;

import com.idfinance.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponse {
    private Long id;
    private String username;

    public static CreateUserResponse from(User user) {
        return CreateUserResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }
}
