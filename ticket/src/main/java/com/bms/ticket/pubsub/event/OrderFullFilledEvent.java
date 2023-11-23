package com.bms.ticket.pubsub.event;

import javax.validation.constraints.NotEmpty;

import java.time.LocalTime;

public class OrderFullFilledEvent extends OrderProcessedEvent{
    @NotEmpty
    private LocalTime oderFullFilledTime;
}
