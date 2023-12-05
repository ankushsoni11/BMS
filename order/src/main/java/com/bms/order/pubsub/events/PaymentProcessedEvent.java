package com.bms.order.pubsub.events;

import lombok.Data;

@Data
public class PaymentProcessedEvent extends OrderCreateEvent {
    private String transactionId;
    private String payerName;
    private String payerEmail;
    private double amount;
    private String currency;
    private String description;
}
