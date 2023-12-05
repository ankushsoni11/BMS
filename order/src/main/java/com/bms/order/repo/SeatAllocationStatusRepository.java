package com.bms.order.repo;

import com.bms.order.entity.SeatAllocationStatus;
import com.bms.order.entity.SeatAllocationStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAllocationStatusRepository extends JpaRepository<SeatAllocationStatus, SeatAllocationStatusId> {
}
