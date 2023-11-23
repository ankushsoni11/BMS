package com.bms.ticket.service;


import com.bms.ticket.domainobject.OrderCreateRequest;
import com.bms.ticket.domainobject.OrderCreateResponse;
import com.bms.ticket.entity.Order;
import com.bms.ticket.util.OrderAction;

import java.math.BigInteger;

public interface TicketService {
    OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception;

    Order cancelOrder(BigInteger orderId) throws IllegalStateException;

    Order updateOrderAction(BigInteger orderId, OrderAction newStatus) throws IllegalStateException;

}
