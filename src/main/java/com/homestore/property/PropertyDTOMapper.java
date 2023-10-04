package com.homestore.property;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class PropertyDTOMapper implements Function<Property, PropertyResponse> {

    @Override
    public PropertyResponse apply(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getUserId(),
                property.getAddress(),
                property.getArea(),
                property.getDescription(),
                property.getCategory(),
                property.getFloor(),
                property.getFloorsNumber(),
                property.getBuildYear(),
                property.getRoomsNumber(),
                property.getBathroomsNumber(),
                property.getPartitioning()
        );
    }
}