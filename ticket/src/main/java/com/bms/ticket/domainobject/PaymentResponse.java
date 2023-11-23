package com.bms.ticket.domainobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.bms.ticket.util.Status;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponse {
    private String transactionId;
    private Integer payementGtwId;
    private Map<String, Object> metaData;
    private Status paymentStatus;
}
