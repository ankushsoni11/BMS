package com.bms.ticket.pubsub.event;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import com.bms.ticket.util.Status;

import java.time.LocalTime;
@Data
public class BaseOrderEvent {
    @NotEmpty
    private LocalTime oderCreatedTime=LocalTime.now();
    @NotEmpty
    private String cartId;
    /*0-pending,1-processing, 2-success, 3-failed*/
    @NotEmpty
    private Status status=Status.pending;
    private String reason;
}
