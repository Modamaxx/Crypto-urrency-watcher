package com.idfinance.repository;

import com.idfinance.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    boolean existsById(Long id);

    Optional<Coin> findBySymbol(String symbol);

}
