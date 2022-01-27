package com.idfinance.service;

import com.idfinance.dto.coin.CoinResponse;
import com.idfinance.exception.ExpectedException;
import com.idfinance.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository repository;

    public Page<CoinResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(CoinResponse::from);
    }

    public CoinResponse getById(Long id) {
        return repository.findById(id)
                .map(CoinResponse::from)
                .orElseThrow(() -> new ExpectedException("could not get coin by id: " + id));
    }
}
