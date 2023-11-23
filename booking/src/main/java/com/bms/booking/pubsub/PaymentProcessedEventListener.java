package com.bms.booking.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.bms.booking.domainObject.BookShowRequest;
import com.bms.booking.domainObject.BookShowResponse;
import com.bms.booking.pubsub.events.OrderCreateEvent;
import com.bms.booking.service.CartService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentProcessedEventListener implements ChannelAwareMessageListener {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CartService cartService;

    @RabbitListener(queues = "${order.payment.processed_queue}")
    public void onMessage(Message message, Channel channel) throws Exception {
        try {

            String payload = new String(message.getBody(), "UTF-8");
            Optional<OrderCreateEvent> deserializedObject = convertJsonToObject(payload);
            // Process the order creation event here
            BookShowResponse response=cartService.lockCartSeats(deserializedObject.get().getCartId(),new BookShowRequest());
            // If processing is successful, acknowledge the message
            // create OderFailEvent in case of the failure
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // Handle errors and optionally reject the message
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    

    private Optional<OrderCreateEvent> convertJsonToObject(String message) throws JsonProcessingException {
        Optional<OrderCreateEvent> myObject=Optional.empty();
            myObject =Optional.of(objectMapper.readValue(message, OrderCreateEvent.class));
        return myObject;
    }
}