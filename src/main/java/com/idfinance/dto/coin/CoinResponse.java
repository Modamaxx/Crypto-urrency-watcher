package com.idfinance.dto.coin;

import com.idfinance.model.Coin;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinResponse {
    private Long id;
    private String symbol;
    private Double actualPrice;

    public static CoinResponse from(Coin coin) {
        return CoinResponse.builder()
                .id(coin.getId())
                .symbol(coin.getSymbol())
                .actualPrice(coin.getActualPrice())
                .build();
    }
}
