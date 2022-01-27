package com.idfinance.service;

import com.idfinance.config.NotifierPropertiesConfiguration;
import com.idfinance.dto.external.ExternalCoinResponse;
import com.idfinance.model.Coin;
import com.idfinance.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.util.collections.CollectionHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;


@RequiredArgsConstructor
@Service
@Slf4j
public class ExternalSpecificCoinService {

    private final RestTemplate restTemplate;
    private final NotifierPropertiesConfiguration configuration;
    private final CoinRepository coinRepository;
    @Value("${specific-coin.get-by-id}")
    private String getByIdUrl;

    @PostConstruct
    public void saveAllCryptos() {
        if (configuration == null || isEmpty(configuration.getCrypto())) return;
        List<Coin> newCoins = configuration.getCrypto().stream()
                .map(NotifierPropertiesConfiguration.Cryptos::getId)
                .filter(id -> !coinRepository.existsById(id))
                .map(this::getById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ExternalCoinResponse::toCryptoCurrency)
                .collect(Collectors.toList());
        coinRepository.saveAll(newCoins);
        newCoins.forEach(coin -> log.info("coin was added: " + coin.toString()));
    }

    public Optional<ExternalCoinResponse> getById(Long id) {
        ParameterizedTypeReference<List<ExternalCoinResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<ExternalCoinResponse>> response = restTemplate
                .exchange(getByIdUrl + id, HttpMethod.GET, null, responseType);
        if (!response.getStatusCode().is2xxSuccessful() || isEmpty(response.getBody())) {
            log.warn("could not get coin by id: " + id );
            return Optional.empty();
        }
        return Optional.ofNullable(response.getBody())
                .filter(CollectionHelper::isNotEmpty)
                .map(e -> e.get(0));
    }
}
