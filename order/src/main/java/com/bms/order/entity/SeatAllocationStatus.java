package com.bms.order.entity;

import com.bms.order.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seats_allocation_status", schema = "bms_booking")
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
    private String status;


}
