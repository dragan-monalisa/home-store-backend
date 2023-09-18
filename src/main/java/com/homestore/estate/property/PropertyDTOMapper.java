package com.homestore.estate.property;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class PropertyDTOMapper implements Function<Property, PropertyDTO> {

    @Override
    public PropertyDTO apply(Property property) {
        return new PropertyDTO(
                property.getId(),
                property.getUserId(),
                property.getAddress(),
                property.getUsableArea(),
                property.getDescription()
        );
    }
}