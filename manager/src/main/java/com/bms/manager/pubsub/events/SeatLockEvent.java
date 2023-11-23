package com.bms.manager.pubsub.events;

import lombok.Data;

import java.util.Set;
@Data
public class SeatLockEvent {
    private Integer showId;
    /* no. of seats locked after cart confirmation */
    private Set<Integer> seatsLocked;
    /* no. of seats booked after payment confirmation */
    private Set<Integer> seatsConfirmed;
}
