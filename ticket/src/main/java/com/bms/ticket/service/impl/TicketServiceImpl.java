package com.bms.ticket.service.impl;



import javax.persistence.EntityNotFoundException;


import com.bms.ticket.domainobject.BookShowResponse;
import com.bms.ticket.entity.Order;
import com.bms.ticket.entity.OrderDetail;
import com.bms.ticket.domainobject.OrderCreateRequest;
import com.bms.ticket.domainobject.OrderCreateResponse;
import com.bms.ticket.pubsub.OrderEventPublisher;
import com.bms.ticket.pubsub.event.OrderCreateEvent;
import com.bms.ticket.repo.OrderDetailRepository;
import com.bms.ticket.repo.OrderRepository;
import com.bms.ticket.service.TicketService;
import com.bms.ticket.service.client.BookingServiceApiClient;
import com.bms.ticket.service.client.PaymentServiceApiClient;
import com.bms.ticket.util.Common;
import com.bms.ticket.util.OrderStateMachine;

import com.bms.ticket.util.OrderAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class TicketServiceImpl implements TicketService {
    @Autowired
    private BookingServiceApiClient bookingServiceApiClient;

    @Value("${bms.onboard.API_KEY}")
    public  String onboard_API_KEY;

    @Value("${bms.booking.API_KEY}")
    public  String booking_API_KEY;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentServiceApiClient paymentServiceApiClient;
    @Autowired
    OrderEventPublisher orderEventPublisher;

    @Override
    public OrderCreateResponse createOrder(OrderCreateRequest request) throws Exception {
        BookShowResponse cartDetail = bookingServiceApiClient.getCart(booking_API_KEY,request.getCartId());
        OrderDetail orderDetail = orderDetailRepository.save(new OrderDetail());
        Order order = orderRepository.save(new Order(null, orderDetail, OrderAction.CREATED,
                Common.getTransactionId(),null, null, null, null));
        OrderCreateEvent event=new OrderCreateEvent();event.setOderId(order.getId());
        event.setCartId(cartDetail.getCartId());event.setOderCreatedTime(order.getCreated());
        orderEventPublisher.publishOrderCreateEvent(event);
        return new OrderCreateResponse(order.getId(), order.getOrderPaymentId(), OrderAction.CREATED);
    }

    @Override
    public Order updateOrderAction(BigInteger orderId, OrderAction newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Implement business logic for state transitions and validations
        if (OrderStateMachine.isValidTransition(order.getLastAction(), newStatus)) {
            order.setLastAction(newStatus);
            return orderRepository.save(order);
        } else {
            throw new IllegalStateException("Invalid state transition");
        }
    }
    @Override
    public Order cancelOrder(BigInteger orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return updateOrderAction(orderId, OrderAction.CANCELLED);
    }

}
