package com.homestore.estate.property;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        List<PropertyDTO> properties = propertyService.getAllProperties();

        if(!properties.isEmpty()){
            return ResponseEntity.ok(properties);
        }

        return ResponseEntity.notFound().build();
    }
}
