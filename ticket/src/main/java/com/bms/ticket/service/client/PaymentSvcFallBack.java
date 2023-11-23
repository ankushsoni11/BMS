package com.bms.ticket.service.client;

import com.bms.ticket.domainobject.PaymentRequest;
import com.bms.ticket.domainobject.PaymentResponse;


public class PaymentSvcFallBack implements PaymentServiceApiClient{
    @Override
    public PaymentResponse process(PaymentRequest request) {
        return null;
    }
}
