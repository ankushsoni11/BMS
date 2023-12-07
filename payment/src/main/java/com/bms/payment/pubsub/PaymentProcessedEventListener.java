package com.bms.payment.pubsub;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentProcessedEventListener {
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "payment-event", groupId = "payment-group")
    void processKafkaEvents(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        log.info("Try to process message");
        try {
            log.info("Processed value: " + record.value());
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Error while processing message: {}" + record.value());
            acknowledgment.acknowledge();
        }
    }

}
