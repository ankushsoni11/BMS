package com.bms.manager.domainObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScreenUpsertRequest {
    @NotEmpty
    private String name;

    private Integer frontSeats;

    private Integer middleSeats;

    private Integer backSeats;
    @Positive
    private Integer theatreId;
}
