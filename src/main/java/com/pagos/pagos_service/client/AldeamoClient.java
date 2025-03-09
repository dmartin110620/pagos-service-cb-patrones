package com.pagos.pagos_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AldeamoClient {

    @Value("${service.aldeamo.url}")
    private String serviceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String sendNotification() {
        return restTemplate.postForObject(serviceUrl + "/notification", null, String.class);
    }
}
