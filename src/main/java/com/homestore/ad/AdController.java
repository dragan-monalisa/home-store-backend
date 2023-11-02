package com.homestore.ad;

import com.homestore.property.PropertyResponse;
import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ads")
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<Page<AdResponse>> getAds(Pageable pageable) {
        return ResponseEntity.ok(adService.getAds(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdResponse> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<AdResponse>> getAdsByFilters(@RequestBody SearchCriteria searchCriteria, Pageable pageable) {
        return ResponseEntity.ok(adService.getAdsByFilters(searchCriteria, pageable));
    }

    @GetMapping("/my-ads")
    public ResponseEntity<Page<AdResponse>> getMyAds(@AuthenticationPrincipal User user,
                                                     @RequestParam String status,
                                                     Pageable pageable) {
        return ResponseEntity.ok(adService.getMyAdsByStatus(user, StatusEnum.valueOf(status), pageable));
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

    @PostMapping
    public ResponseEntity<String> saveAd(@AuthenticationPrincipal User user,
                                         @RequestBody AdRequest request){
        adService.saveAd(user, request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}