package com.bms.order.pubsub;

import com.bms.order.pubsub.events.PaymentProcessedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class PaymentGatewayPublisher {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public void publish(String key, PaymentProcessedEvent message) {
        ObjectMapper objectMapper = new ObjectMapper();
        String convertedString = null;
        try {
            convertedString = convertObjectToJson(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Sending event to order-events {} ", convertedString);
        kafkaTemplate.send("order-events", convertedString);
        // publish message to Kafka i.e. shows.seats.publisher.topic
    }

    private String convertObjectToJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
