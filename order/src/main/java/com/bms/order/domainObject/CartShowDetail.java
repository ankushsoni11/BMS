package com.bms.order.domainObject;

import com.bms.order.util.Common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CartShowDetail implements Serializable {
    private String cartId;
    private String sessionId;
    private Integer cityId;
    private Integer theatreId;
    private Integer audiId;
    private Integer showId;
    private Set<Integer> seats;
    private CartBillDetail cartBillDetail;
    private UserDetail userDetail;

    public static CartShowDetail copy(BookShowRequest obj) {
        CartShowDetail detail = new CartShowDetail();
        detail.setCartId(Common.getCartId());
        detail.setSessionId(obj.getUserId());
        detail.setCityId(obj.getCityId());
        detail.setTheatreId(obj.getTheatreId());
        detail.setAudiId(obj.getAuditId());
        detail.setShowId(obj.getShowId());
        detail.setSeats(obj.getSeats());
        return detail;
    }

    public static CartShowDetail copy(String cartId, BookShowRequest obj) {
        CartShowDetail detail = new CartShowDetail();
        detail.setCartId(cartId);
        detail.setSessionId(obj.getUserId());
        detail.setCityId(obj.getCityId());
        detail.setTheatreId(obj.getTheatreId());
        detail.setAudiId(obj.getAuditId());
        detail.setShowId(obj.getShowId());
        detail.setSeats(obj.getSeats());
        return detail;
    }
}
