package com.bms.ticket.service.client;




import com.bms.ticket.domainobject.BookShowResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bookingSvcClient", fallback = BookingSvcFallBack.class,path = "/booking/v1/carts", url = "http://localhost:9002")
public interface BookingServiceApiClient {
    @GetMapping(value = "/getCart/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    BookShowResponse getCart(@RequestHeader("X-API-Key") String apiKey,@PathVariable String id) throws Exception;
}
