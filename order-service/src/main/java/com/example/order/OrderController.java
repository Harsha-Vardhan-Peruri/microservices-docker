package com.example.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    private final WebClient webClient;

    public OrderController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/order")
    public String createOrder() {

        log.info("API /order called");

        try {
            String paymentResponse = webClient
                    .get()
                    .uri(paymentServiceUrl + "/payment")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("API /order completed with status=200, paymentStatus={}", paymentResponse);

            return "ORDER_CREATED with payment status: " + paymentResponse;

        } catch (Exception ex) {
            log.error("API /order failed with status=500", ex);
            throw ex;
        }
    }

}

