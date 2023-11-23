package com.bms.ticket.service.client;


import com.bms.ticket.domainobject.BookShowResponse;

public class BookingSvcFallBack implements BookingServiceApiClient{
    @Override
    public BookShowResponse getCart(String apiKey,String id) throws Exception {
        return null;
    }
}
