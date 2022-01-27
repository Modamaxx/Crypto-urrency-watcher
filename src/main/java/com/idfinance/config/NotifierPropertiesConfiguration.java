package com.idfinance.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "notifier")
@Getter
@Setter
@Configuration
public class NotifierPropertiesConfiguration {

    private List<Cryptos> crypto;

    @Getter
    @Setter
    public static class Cryptos {
        private Long id;
        private String symbol;
    }
}
