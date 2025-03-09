package com.pagos.pagos_service.controller;

import com.pagos.pagos_service.service.NotificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final NotificationService notificationService;

    public PaymentController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> processPayment() {
        String notificationResult = notificationService.sendNotification();
        return ResponseEntity.ok("Pago procesado. Detalles: " + notificationResult);
    }
}
