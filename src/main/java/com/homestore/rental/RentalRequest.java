package com.homestore.rental;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
    private LocalDate endDate;
    private Long price;
    private Long adId;
    private Long tenantId;
    private String clauses;
}