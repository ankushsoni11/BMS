package com.bms.manager.pubsub.events;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalTime;
@Data
public class BaseOrderEvent {
    @NotEmpty
    private LocalTime oderCreatedTime=LocalTime.now();
    @NotEmpty
    private String cartId;
    /*0-pending,1-processing, 2-success, 3-failed*/
    private String reason;
}
