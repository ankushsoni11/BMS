package com.bms.order.redisCache;

import com.bms.order.exception.SeatNotAvailableException;

import java.util.Set;

public interface SeatLockRepository {
    void blockSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException, SeatNotAvailableException;

    void confirmSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException;
    void unblockSeat(Integer showId, Set<Integer> seats, String lockOwner) throws SeatNotAvailableException;
}
