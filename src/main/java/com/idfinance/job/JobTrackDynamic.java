package com.idfinance.job;

import com.idfinance.model.User;
import com.idfinance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobTrackDynamic {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 * * * * ?")
    public void trackDynamic() {
        log.info("track user dynamic");
        userRepository.findAll()
                .forEach(this::validateUserCoin);
    }

    private void validateUserCoin(User user) {
        double change = calculatePercents(user.getPrice(), user.getCoin().getActualPrice());
        if (change > 1) {
            log.warn("username: " + user.getUsername()
                    + " coin: " + user.getCoin().getSymbol() + " changes: " + change + "%");
            user.setPrice(user.getCoin().getActualPrice());
            userRepository.save(user);
        }
    }

    private Double calculatePercents(Double from, Double to) {
        if (from > to)
            return 100 * (from - to) / from;
        return 100 * (to - from) / to;
    }
}
