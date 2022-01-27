package com.idfinance.controller;

import com.idfinance.dto.coin.CoinResponse;
import com.idfinance.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/coin")
@RequiredArgsConstructor
public class CoinController {

    private final CoinService coinService;

    @GetMapping
    public Page<CoinResponse> findAll(Pageable pageable) {
        return coinService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CoinResponse findById(@PathVariable Long id) {
        return coinService.getById(id);
    }
}
