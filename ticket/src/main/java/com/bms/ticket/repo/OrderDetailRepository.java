package com.bms.ticket.repo;

import com.bms.ticket.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, BigInteger> {
}
