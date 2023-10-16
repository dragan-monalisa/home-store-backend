package com.homestore.ad;

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

    @GetMapping("/filter")
    public ResponseEntity<List<AdResponse>> getAdsByFilters(@RequestBody SearchCriteria searchCriteria) {
        return ResponseEntity.ok(adService.getAdsByFilters(searchCriteria));
    }

    @GetMapping("/my-ads")
    public ResponseEntity<List<AdResponse>> getMyAds(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(adService.getMyAds(user));
    }

    @GetMapping("/my-ads/status")
    public ResponseEntity<List<AdResponse>> getMyAdsByStatus(@AuthenticationPrincipal User user,
                                                             @RequestBody String status) {
        return ResponseEntity.ok(adService.getMyAdsByStatus(user, StatusEnum.valueOf(status)));
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