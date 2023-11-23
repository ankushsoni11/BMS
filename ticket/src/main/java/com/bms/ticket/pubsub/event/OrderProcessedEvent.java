package com.bms.ticket.pubsub.event;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalTime;

@Data
public class OrderProcessedEvent extends OrderCreateEvent {
    @NotEmpty
    private LocalTime oderProcessedTime;

}
