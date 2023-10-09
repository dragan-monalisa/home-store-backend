package com.homestore.property;

import com.homestore.ad.category.PropertyCategoryEnum;
import com.homestore.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyResponse {
    private Long id;
    private Long userId;
    private Address address;
    private Integer area;
    private String description;
    private PropertyCategoryEnum category;
    private Integer floor;
    private Integer floorsNumber;
    private Integer buildYear;
    private Integer roomsNumber;
    private Integer bathroomsNumber;
    private String partitioning;
}