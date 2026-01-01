package com.example.payment_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping("/payment")
    public String makePayment() {

        log.info("API /payment called");

        log.info("API /payment completed with status=200");

        return "PAYMENT_SUCCESS";
    }

}

