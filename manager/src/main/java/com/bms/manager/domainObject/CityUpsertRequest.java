package com.bms.manager.domainObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CityUpsertRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String district;
    @NotEmpty
    private String state;
    @Positive
    private Integer countryId;
}
