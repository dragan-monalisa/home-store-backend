package com.homestore.ad;

import com.homestore.ad.category.AdCategoryEnum;
import com.homestore.ad.category.PropertyCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdSearchCriteria {
    AdCategoryEnum adCategory;
    PropertyCategoryEnum propertyCategory;
    String county;
    String city;
    Long minPrice;
    Long maxPrice;
    Integer minUsableArea;
    Integer maxUsableArea;
}