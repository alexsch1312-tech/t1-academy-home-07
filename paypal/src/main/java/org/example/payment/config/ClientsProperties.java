package org.example.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clients")
public class ClientsProperties {

    private final RestTemplateProperties paymentClient;

    public ClientsProperties(RestTemplateProperties paymentClient) {
        this.paymentClient = paymentClient;
    }

    public RestTemplateProperties getPaymentClient() {
        return paymentClient;
    }
}
