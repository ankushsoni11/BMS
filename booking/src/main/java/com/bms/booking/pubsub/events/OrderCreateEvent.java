package com.bms.booking.pubsub.events;

import javax.validation.constraints.Positive;
import lombok.Data;

import java.math.BigInteger;

@Data
public class OrderCreateEvent extends BaseOrderEvent {
    @Positive
    private BigInteger orderId;

}
