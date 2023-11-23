package com.bms.manager.domainObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TheatreGetDeleteRequest {
    @NotEmpty
    private String ownerId;
    @Positive
    Integer theaterId;
}
