package com.idfinance.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @JoinColumn(name = "coin_id")
    @ManyToOne
    private Coin coin;

    @Column(name = "price")
    private Double price;
}
