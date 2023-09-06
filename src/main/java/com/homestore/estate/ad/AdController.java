package com.homestore.estate.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/ads")
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<List<AdDTO>> getAllAds(){
        List<AdDTO> ads = adService.getAllAds();

        if(!ads.isEmpty()){
            return ResponseEntity.ok(ads);
        }

        return ResponseEntity.notFound().build();
    }
}
