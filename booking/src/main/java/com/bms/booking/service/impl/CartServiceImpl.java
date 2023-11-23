package com.bms.booking.service.impl;


import com.bms.booking.domainObject.BookShowRequest;
import com.bms.booking.domainObject.BookShowResponse;
import com.bms.booking.domainObject.CartShowDetail;
import com.bms.booking.pubsub.ShowsSeatLockPublisher;
import com.bms.booking.pubsub.events.SeatLockEvent;
import com.bms.booking.redisCache.CartBookingCacheRepository;
import com.bms.booking.redisCache.SeatLockRepository;
import com.bms.booking.service.CartOperations;
import com.bms.booking.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartOperations cartOperations;
    @Autowired
    private CartBookingCacheRepository cartBookingCache;
    @Autowired
    private SeatLockRepository seatLockRepository;
    @Autowired
    private ShowsSeatLockPublisher seatLockPublisher;

    @Override
    public BookShowResponse saveCart(BookShowRequest request) throws Exception {
        CartShowDetail cartDetail = CartShowDetail.copy(request);
        cartOperations.verifyCart(cartDetail);
        cartOperations.computeBill(cartDetail);
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        BookShowResponse response = new BookShowResponse(cartDetail.getCartId(), request.getUserId(),
                request.getCityId(), request.getTheatreId(), request.getAuditId(), request.getShowId(), request.getSeats(),
                cartDetail.getCartBillDetail());
        return response;
    }

    @Override

    public BookShowResponse updateCart(String id, BookShowRequest request) throws Exception {
        CartShowDetail cartDetail = CartShowDetail.copy(id, request);
        cartBookingCache.isBookingDetailExists(id);
        cartOperations.verifyCart(cartDetail);
        cartOperations.computeBill(cartDetail);
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        BookShowResponse response = new BookShowResponse(cartDetail.getCartId(), request.getUserId(),
                request.getCityId(), request.getTheatreId(), request.getAuditId(), request.getShowId(), request.getSeats(),
                cartDetail.getCartBillDetail());
        return response;
    }

    @Override
    public BookShowResponse confirmCart(String id, BookShowRequest request) throws Exception {
        CartShowDetail cartDetail = CartShowDetail.copy(id, request);
        cartBookingCache.isBookingDetailExists(id);
        cartOperations.verifyCart(cartDetail);
        cartOperations.computeBill(cartDetail);
        try {
            seatLockRepository.blockSeat(cartDetail.getShowId(), cartDetail.getSeats(), cartDetail.getCartId());
        } catch (Exception snae) {
            seatLockRepository.unblockSeat(cartDetail.getShowId(), cartDetail.getSeats(), cartDetail.getCartId());
            throw snae;
        }
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        SeatLockEvent event = new SeatLockEvent();
        event.setSeatsLocked(cartDetail.getSeats());
        event.setCartId(cartDetail.getCartId());
        event.setShowId(cartDetail.getShowId());
        event.setSeatsConfirmed(cartDetail.getSeats());
        seatLockPublisher.publish(cartDetail.getShowId().toString(), event);
        BookShowResponse response = new BookShowResponse(cartDetail.getCartId(), request.getUserId(),
                request.getCityId(), request.getTheatreId(), request.getAuditId(), request.getShowId(), request.getSeats(),
                cartDetail.getCartBillDetail());
        return response;
    }

    @Override
    public BookShowResponse lockCartSeats(String id, BookShowRequest request) throws Exception {
        // CartShowDetail cartDetail = CartShowDetail.copy(id, request);
        cartBookingCache.isBookingDetailExists(id);
        CartShowDetail cartDetail = cartBookingCache.getBookingDetail(id);
        cartOperations.verifyCart(cartDetail);
        cartOperations.computeBill(cartDetail);
        try {
            seatLockRepository.blockSeat(cartDetail.getShowId(), cartDetail.getSeats(), cartDetail.getCartId());
        } catch (Exception snae) {
            seatLockRepository.unblockSeat(cartDetail.getShowId(), cartDetail.getSeats(), cartDetail.getCartId());
            throw snae;
        }
        cartBookingCache.saveBookingDetail(cartDetail.getCartId(), cartDetail);
        SeatLockEvent event = new SeatLockEvent();
        event.setSeatsLocked(cartDetail.getSeats());
        event.setCartId(cartDetail.getCartId());
        event.setShowId(cartDetail.getShowId());
        seatLockPublisher.publish(cartDetail.getShowId().toString(), event);
        BookShowResponse response = new BookShowResponse(cartDetail.getCartId(), request.getUserId(),
                request.getCityId(), request.getTheatreId(), request.getAuditId(), request.getShowId(), request.getSeats(),
                cartDetail.getCartBillDetail());
        return response;
    }

    @Override
    public BookShowResponse getCart(String id) throws Exception {
        cartBookingCache.isBookingDetailExists(id);
        CartShowDetail cartDetail = cartBookingCache.getBookingDetail(id);
        return BookShowResponse.copy(cartDetail);
    }

    @Override
    public void deleteCart(String id) throws Exception {
        cartBookingCache.isBookingDetailExists(id);
        cartBookingCache.deleteBookingDetail(id);
    }

}
