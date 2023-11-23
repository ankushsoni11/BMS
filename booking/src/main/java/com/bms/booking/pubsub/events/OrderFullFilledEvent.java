package com.bms.booking.pubsub.events;

import javax.validation.constraints.NotEmpty;

import java.time.LocalTime;

public class OrderFullFilledEvent extends OrderProcessedEvent {
    @NotEmpty
    private LocalTime orderFullFilledTime;
}
