package com.homestore.estate.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Long id;
    private Long price;
    private Long propertyId;
    private Long adId;
    private Long buyerId;
    private Date date;
}
