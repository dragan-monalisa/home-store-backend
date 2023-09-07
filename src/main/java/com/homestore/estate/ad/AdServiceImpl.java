package com.homestore.estate.ad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;
    private final AdDTOMapper adMapper;

    @Override
    public List<AdDTO> getAllAds() {
        return adRepository.findAll()
                .stream()
                .map(adMapper)
                .collect(Collectors.toList());
    }

    @Override
    public AdDTO getAdById(Long id) {
        Optional<Ad> ad = adRepository.findById(id);

        return ad.map(adMapper).orElse(null);
    }
}
