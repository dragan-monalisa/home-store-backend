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
public class RentalRequest {
    private LocalDateTime endDate;
    private Long price;
    private Long adId;
    private Long tenantId;
    private String clauses;
}