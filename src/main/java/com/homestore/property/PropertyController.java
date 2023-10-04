package com.homestore.property;

import com.homestore.security.user.User;
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
    public ResponseEntity<List<PropertyResponse>> getProperties(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(propertyService.getProperties(user.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id){
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @PostMapping
    public ResponseEntity<String> saveProperty(@AuthenticationPrincipal User user,
                                               @RequestBody PropertyRequest request){
        propertyService.saveProperty(user, request);

        return new ResponseEntity<>("Property successfully saved!", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id,
                                                           @RequestBody PropertyRequest request){
        propertyService.updateProperty(id, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id){
        propertyService.deleteProperty(id);

        return ResponseEntity.noContent().build();
    }
}