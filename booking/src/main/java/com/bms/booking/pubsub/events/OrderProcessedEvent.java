package com.bms.booking.pubsub.events;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class OrderProcessedEvent extends OrderCreateEvent {
    @NotEmpty
    private LocalTime orderProcessedTime;

}
