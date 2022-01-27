package com.idfinance.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.idfinance.model.Coin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalCoinResponse {
    private Long id;
    private String symbol;
    @JsonProperty("price_usd")
    private Double price;

    @JsonIgnore
    public Coin toCryptoCurrency() {
        return Coin.builder()
                .id(id)
                .symbol(symbol)
                .actualPrice(price)
                .build();
    }
}
