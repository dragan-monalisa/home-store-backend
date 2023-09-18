package com.homestore.estate.property;

import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getMyProperties(@AuthenticationPrincipal User user){
        List<PropertyDTO> properties = propertyService.getProperties(user.getId());

        if(!properties.isEmpty()){
            return ResponseEntity.ok(properties);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id){
        PropertyDTO property = propertyService.getPropertyById(id);

        if(property != null){
            return ResponseEntity.ok(property);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> saveProperty(@AuthenticationPrincipal User user,
                                               @RequestBody PropertyRequest request){
        propertyService.saveProperty(user, request);

        return ResponseEntity.ok("Property successfully saved!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProperty(@PathVariable Long id,
                                                 @RequestBody PropertyRequest request){
        String response = propertyService.updateProperty(id, request);

        if(ResponseEnum.UPDATED.name().equals(response)){
            return ResponseEntity.ok("Property successfully updated!");
        }

        return new ResponseEntity<>("Please provide valid property data!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id){
        String response = propertyService.deleteProperty(id);

        if(ResponseEnum.DELETED.name().equals(response)){
            return ResponseEntity.ok("Property successfully deleted!");
        }

        return new ResponseEntity<>("Property not found!", HttpStatus.NOT_FOUND);
    }
}