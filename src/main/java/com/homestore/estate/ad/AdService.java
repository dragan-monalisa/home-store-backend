package com.homestore.estate.ad;

import java.util.List;
import java.util.Optional;

public interface AdService {
    List<AdDTO> getAllAds();
    AdDTO getAdById(Long id);
}
