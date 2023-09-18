package com.homestore.estate.property;

import com.homestore.estate.ad.category.EstateCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private Integer usableArea;
    private String description;
    private String county;
    private String city;
    private String street;
    private Integer number;
    private EstateCategory category;

    //Apartment
    private String partitioning;
    private Integer floor;
    private Integer buildYear;
    private Integer roomsNumber;

    //House
    private Integer storeys;
}