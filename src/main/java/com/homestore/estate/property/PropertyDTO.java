package com.homestore.estate.property;

import com.homestore.estate.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private Long id;
    private Long userId;
    private Address address;
    private Integer usableArea;
    private String description;
}