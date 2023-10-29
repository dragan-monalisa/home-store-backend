package com.homestore.ad;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homestore.ad.category.AdCategoryEnum;
import com.homestore.property.PropertyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdResponse {
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;

    private StatusEnum status;
    private String title;
    private Long price;
    private AdCategoryEnum category;
    private Long realtorId;
    private PropertyResponse property;
}