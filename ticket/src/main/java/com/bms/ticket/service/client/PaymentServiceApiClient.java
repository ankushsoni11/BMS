package com.bms.ticket.service.client;

import javax.validation.Valid;
import com.bms.ticket.domainobject.PaymentRequest;

import com.bms.ticket.domainobject.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentSvcClient", path = "/payment-service/v1/payments",
        url = "https://paymentgeateway", fallback = PaymentSvcFallBack.class)
public interface PaymentServiceApiClient {


    @PostMapping("/process")
    PaymentResponse process(@Valid @RequestBody PaymentRequest request);
}
