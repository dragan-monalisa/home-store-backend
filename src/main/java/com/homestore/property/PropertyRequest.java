package com.homestore.property;

import com.homestore.ad.category.PropertyCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private Integer area;
    private String description;
    private String county;
    private String city;
    private String street;
    private Integer number;
    private PropertyCategoryEnum category;

    private Integer floor;
    private Integer floorsNumber;
    private Integer buildYear;
    private Integer roomsNumber;
    private Integer bathroomsNumber;
    private String partitioning;
}