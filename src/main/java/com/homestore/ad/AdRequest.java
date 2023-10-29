package com.homestore.ad;

import com.homestore.ad.category.AdCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdRequest {
    private StatusEnum status;
    private String title;
    private Long price;
    private AdCategoryEnum category;
    private Long propertyId;
    private Long realtorId;
}