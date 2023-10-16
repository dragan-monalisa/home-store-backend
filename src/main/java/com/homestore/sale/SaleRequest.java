package com.homestore.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {
    private Long price;
    private Long adId;
    private Long buyerId;
    private String clauses;
}