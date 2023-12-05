package com.bms.order.service;


import com.bms.order.domainObject.CartBillDetail;
import com.bms.order.domainObject.CartShowDetail;
import com.bms.order.exception.CartVerificationException;
import com.bms.order.exception.SeatNotAvailableException;
import com.bms.order.service.client.OnboardServiceApiClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class CartOperations {
    @Autowired
    private OnboardServiceApiClient catalogServiceApiClient;

    @Value("${bms.onboard.API_KEY}")
    public String API_KEY;


    public void verifyCart(CartShowDetail CartDetail) throws CartVerificationException, SeatNotAvailableException {
        //verify Cart items
        // check promos
        //check seats capacity
        checkSeatCapacity(CartDetail.getShowId(), CartDetail.getSeats());
    }

    @CircuitBreaker(name = "onboardCheckSeatCapacity", fallbackMethod = "onboardCheckSeatCapacityFallback")
    @Retry(name = "retryToCheckSeatCapacity", fallbackMethod = "onboardCheckSeatCapacityFallBack")
    private void checkSeatCapacity(Integer showId, Set<Integer> seats) throws SeatNotAvailableException {
        log.debug(API_KEY);
        Integer freeSeatsCount = catalogServiceApiClient.getFreeSeatsCount(API_KEY, showId);
        if (freeSeatsCount < seats.size())
            throw new SeatNotAvailableException(String.format("Sufficient seats not available %s", freeSeatsCount));
        else {
            log.info("{} seat are available", freeSeatsCount);
        }
    }

    private void onboardCheckSeatCapacityFallBack() throws SeatNotAvailableException {
        throw new SeatNotAvailableException(String.format("Fallback called while checking  seats availability"));
    }

    public void computeBill(CartShowDetail CartDetail) {
        //1. compute discounts, taxes, final bill amount
        CartDetail.setCartBillDetail(new CartBillDetail());
    }

}
