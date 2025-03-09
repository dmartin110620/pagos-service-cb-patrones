package com.pagos.pagos_service.service;

import com.pagos.pagos_service.client.AldeamoClient;
import com.pagos.pagos_service.client.TwilioClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final AldeamoClient aldeamoClient;
    private final TwilioClient twilioClient;
    private final FileWriterService fileWriterService;

    public NotificationService(AldeamoClient aldeamoClient,
            TwilioClient twilioClient,
            FileWriterService fileWriterService) {
        this.aldeamoClient = aldeamoClient;
        this.twilioClient = twilioClient;
        this.fileWriterService = fileWriterService;
    }

    @CircuitBreaker(name = "notificationCB", fallbackMethod = "fallbackNotification")
    public String sendNotification() {
        String response = aldeamoClient.sendNotification(); // Intenta Aldeamo
        fileWriterService.writeToFile("Notificación enviada por Aldeamo: " + response);
        return response;
    }

    public String fallbackNotification(Throwable t) {
        String response = twilioClient.sendNotification(); // Usa Twilio si Aldeamo falla
        fileWriterService.writeToFile("Notificación enviada por Twilio: " + response);
        return response;
    }

}
