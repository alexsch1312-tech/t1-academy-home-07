package org.example.payment.config;

import org.example.payment.exception.IntegrationException;
import org.example.payment.exception.ProductNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        String path = url.getPath();
        String query = url.getQuery();

        if (statusCode == HttpStatus.NOT_FOUND) {
            String id = getID(path);
            throw new ProductNotFoundException("Продукты с ID " + id + " не найдены в сервисе продуктов.");
        }

        if (path.startsWith("/api/v1/products")) {

            if (method == HttpMethod.GET && query != null && query.contains("userId")) {
                throw new IntegrationException("Не удалось получить продукты пользователя: Ошибка сервиса продуктов. Статус: " + statusCode);
            }

            if (method == HttpMethod.PUT) {
                throw new IntegrationException("Не удалось обновить баланс продукта: Ошибка сервиса продуктов. Статус: " + statusCode);
            }

            throw new IntegrationException("Ошибка при обращении к сервису продуктов: Статус " + statusCode);
        }

        // Для всего остального
        throw new IntegrationException("Непредвиденная ошибка интеграции: Статус " + statusCode);
    }

    private String getID(String path) {
        try {
            String[] pathItem = path.split("/");
            return pathItem[pathItem.length - 1];
        } catch (Exception e) {
            return "нет";
        }
    }
}
