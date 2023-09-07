package com.homestore.estate.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long price;
    private Long propertyId;
    private Long adId;
    private Long tenantId;
}
