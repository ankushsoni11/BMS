package com.bms.manager.pubsub;

import com.bms.manager.pubsub.events.SeatConfirmEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class SeatConfirmEventListener {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @KafkaListener(topics = "order-events", groupId = "order-event-group")
    public void onMessage(String message) throws Exception {
        try {

            Optional<SeatConfirmEvent> deserializedObject = convertJsonToObject(message);
            // Confirm the seat in the DB, and update the cache for read by sending event
            log.info("Seat Confirmed in the cache :: {}", message);


        } catch (Exception e) {
            // Handle errors and optionally reject the message
            log.info("Seat Confirmed", e);
        }
    }

    private Optional<SeatConfirmEvent> convertJsonToObject(String message) throws JsonProcessingException {
        Optional<SeatConfirmEvent> myObject = Optional.empty();
        myObject = Optional.of(objectMapper.readValue(message, SeatConfirmEvent.class));
        return myObject;
    }
}