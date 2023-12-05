package com.bms.order.pubsub.events;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class OrderCreateEvent extends BaseOrderEvent {
    @Positive
    private BigInteger orderId;

}
