package com.bms.order.pubsub.events;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
public class OrderProcessedEvent extends OrderCreateEvent {
    @NotEmpty
    private LocalTime orderProcessedTime;

}
