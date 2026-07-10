package org.example.payment.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(ClientsProperties.class)
public class PaymentConfig {

    private final ClientsProperties clientsProperties;

    public PaymentConfig(ClientsProperties clientsProperties) {
        this.clientsProperties = clientsProperties;
    }

    @Bean
    public RestTemplate paymentClient() {

        RestTemplateProperties props = clientsProperties.getPaymentClient();

        return new RestTemplateBuilder()
                .baseUri(props.url())
                .connectTimeout(props.connectTimeout())
                .readTimeout(props.readTimeout())
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }
}