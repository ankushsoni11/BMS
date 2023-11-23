package com.bms.booking.repo;

import com.bms.booking.entity.SeatAllocationStatus;
import com.bms.booking.entity.SeatAllocationStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAllocationStatusRepository extends JpaRepository<SeatAllocationStatus, SeatAllocationStatusId> {
}
