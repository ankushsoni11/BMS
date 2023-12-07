package com.bms.order.pubsub;


import com.bms.order.pubsub.events.OrderCreateEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class OrderCreatePublisher {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public OrderCreatePublisher(KafkaTemplate kafkaTemplate) {
        // this.rabbitTemplate = rabbitTemplate;
    }


    public void publish(String key, OrderCreateEvent message) {
        ObjectMapper objectMapper = new ObjectMapper();
        String convertedString = null;
        try {
            convertedString = convertObjectToJson(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Sending Message to order-events topic {}", convertedString);
        CompletableFuture<SendResult<String, String>> future
                = kafkaTemplate.usingCompletableFuture().send("order-events", convertedString);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");

            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }


    private String convertObjectToJson(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
