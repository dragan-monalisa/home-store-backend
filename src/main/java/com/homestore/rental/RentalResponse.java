package com.homestore.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long price;
    private Long propertyId;
    private Long adId;
    private Long tenantId;
}