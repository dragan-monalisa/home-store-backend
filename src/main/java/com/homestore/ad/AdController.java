package com.homestore.ad;

import com.homestore.ad.category.AdCategoryEnum;
import com.homestore.ad.category.PropertyCategoryEnum;
import com.homestore.property.PropertyResponse;
import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ads")
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<List<AdResponse>> getAds() {
        return ResponseEntity.ok(adService.getAds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdResponse> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @GetMapping
    public ResponseEntity<List<AdResponse>> searchAdsByCriteria(@RequestParam(name = "adCategory", required = false) String adCategory,
                                                                @RequestParam(name = "propertyCategory", required = false) String propertyCategory,
                                                                @RequestParam(name = "county", required = false) String county,
                                                                @RequestParam(name = "city", required = false) String city,
                                                                @RequestParam(name = "minPrice", required = false) Long minPrice,
                                                                @RequestParam(name = "maxPrice", required = false) Long maxPrice,
                                                                @RequestParam(name = "minUsableArea", required = false) Integer minUsableArea,
                                                                @RequestParam(name = "maxUsableArea", required = false) Integer maxUsableArea) {
        AdSearchCriteria searchCriteria = AdSearchCriteria.builder()
                .adCategory(AdCategoryEnum.valueOf(adCategory))
                .propertyCategory(PropertyCategoryEnum.valueOf(propertyCategory))
                .county(county)
                .city(city)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .minUsableArea(minUsableArea)
                .maxUsableArea(maxUsableArea)
                .build();

        return ResponseEntity.ok(adService.getAdsByCriteria(searchCriteria));
    }

    @GetMapping("/myAds")
    public ResponseEntity<List<AdResponse>> getMyAds(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(adService.getMyAds(user));
    }

    @GetMapping("/myAdsByStatus")
    public ResponseEntity<List<AdResponse>> getMyAdsByStatus(@AuthenticationPrincipal User user,
                                                             @RequestBody AdRequest request) {
        return ResponseEntity.ok(adService.getMyAdsByStatus(user, request.getStatus()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateAd(@AuthenticationPrincipal User user,
                                                     @PathVariable Long id,
                                                     @RequestBody AdRequest request){
        adService.updateAd(user, id, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAd(@AuthenticationPrincipal User user,
                                           @PathVariable Long id){
        adService.deleteAd(user, id);

        return ResponseEntity.noContent().build();
    }
}