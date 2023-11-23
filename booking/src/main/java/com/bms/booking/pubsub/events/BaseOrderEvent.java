package com.bms.booking.pubsub.events;

import javax.validation.constraints.NotEmpty;

import com.bms.booking.util.Status;
import lombok.Data;


import java.time.LocalTime;
@Data
public class BaseOrderEvent {
    @NotEmpty
    private LocalTime orderCreatedTime = LocalTime.now();
    @NotEmpty
    private String cartId;
    /*0-pending,1-processing, 2-success, 3-failed*/
    @NotEmpty
    private Status status= Status.pending;
    private String reason;
}
