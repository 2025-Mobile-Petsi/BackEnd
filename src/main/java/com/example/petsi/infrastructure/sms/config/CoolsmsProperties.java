package com.example.petsi.infrastructure.sms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "coolsms")
@Getter
@Setter
public class CoolsmsProperties {
    private String apiKey;
    private String apiSecret;
    private String from;
}
