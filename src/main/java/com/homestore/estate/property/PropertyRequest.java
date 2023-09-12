package com.homestore.estate.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private int usableArea;
    private String description;
    private String county;
    private String city;
    private String street;
    private int number;
    private int category;
    private String buildYear;
    private String partitioning;
    private String floor;
    private String roomsNumber;
    private String storeys;
}
