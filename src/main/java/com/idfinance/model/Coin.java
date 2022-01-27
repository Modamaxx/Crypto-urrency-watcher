package com.idfinance.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(of = {"symbol", "actualPrice"})
public class Coin {
    @Id
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "actualPrice")
    private Double actualPrice;

}
