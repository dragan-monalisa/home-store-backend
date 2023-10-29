package com.homestore.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homestore.ad.AdResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    private Long id;
    private Long price;
    private Long propertyId;
    private AdResponse ad;
    private Long buyerId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime date;
}