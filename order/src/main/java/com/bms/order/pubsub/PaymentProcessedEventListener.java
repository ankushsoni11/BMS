package com.bms.order.pubsub;

import com.bms.order.pubsub.events.OrderCreateEvent;
import com.bms.order.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class PaymentProcessedEventListener {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CartService cartService;

    @KafkaListener(topics = "payment-event", groupId = "paymentGroup")
    public void onMessage(String message) throws Exception {
        try {

            Optional<OrderCreateEvent> deserializedObject = convertJsonToObject(message);
            // Process the order creation event here
            log.info("Payment Received :: {}", message);
        } catch (Exception e) {
            // Handle errors and optionally reject the message
            log.info("Payment Exception", e);
        }
    }


    private Optional<OrderCreateEvent> convertJsonToObject(String message) throws JsonProcessingException {
        Optional<OrderCreateEvent> myObject = Optional.empty();
        myObject = Optional.of(objectMapper.readValue(message, OrderCreateEvent.class));
        return myObject;
    }
}
