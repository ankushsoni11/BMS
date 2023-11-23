package com.bms.booking.service.client;

public class OnboardFallback implements OnboardServiceApiClient {

    @Override
    public Integer getFreeSeatsCount(String apiKey, Integer showId) {
        return null;
    }
}
