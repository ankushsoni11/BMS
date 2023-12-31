package com.bms.order.domainObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookShowRequest {
    @NotEmpty
    private String userId;
    @Positive
    private Integer showId;
    @NotEmpty
    private Set<Integer> seats;
    @Positive
    private Integer cityId;
    @Positive
    private Integer theatreId;
    @Positive
    private Integer auditId;
}
