package com.idfinance.job;

import com.idfinance.model.Coin;
import com.idfinance.repository.CoinRepository;
import com.idfinance.service.ExternalSpecificCoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class JobTrackExternalUpdates {

    private final CoinRepository coinRepository;
    private final ExternalSpecificCoinService externalService;

    @Scheduled(cron = "0 * * * * ?")
    public void trackDynamic() {
        log.info("update coin price");
        coinRepository.findAll()
                .forEach(this::updatePrice);
    }

    private void updatePrice(Coin coin) {
        externalService.getById(coin.getId())
                .ifPresentOrElse(newCoin -> {
                    coin.setActualPrice(newCoin.getPrice());
                    coinRepository.save(coin);
                }, () -> log.info("could not get new coin price by id " + coin.getId()));
    }

}
