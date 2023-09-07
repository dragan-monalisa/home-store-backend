package com.homestore.estate.ad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdSearchCriteria {
    String category;
    String type;
    String county;
    String city;
    Long minPrice;
    Long maxPrice;
    Integer minUsableArea;
    Integer maxUsableArea;
}
