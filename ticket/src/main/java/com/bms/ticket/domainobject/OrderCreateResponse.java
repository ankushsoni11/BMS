package com.bms.ticket.domainobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.bms.ticket.util.OrderAction;


import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderCreateResponse {
    private BigInteger orderId;
    private String paymentTransactionId;
    private OrderAction nextAction;
}
