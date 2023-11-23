package com.bms.booking.entity;

import javax.persistence.*;

import com.bms.booking.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats_allocation_status")
@IdClass(SeatAllocationStatusId.class)
public class SeatAllocationStatus {
    @Id
    @Column(name = "show_id")
    private Integer showId;

    @Id
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "status")
    private Status status;


}
