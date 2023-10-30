package com.homestore.rental;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homestore.ad.AdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    private Long price;
    private Long propertyId;
    private AdResponse ad;
    private Long tenantId;
}