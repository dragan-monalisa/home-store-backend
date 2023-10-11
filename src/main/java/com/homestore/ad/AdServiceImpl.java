package com.homestore.ad;

import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnauthorizedAccessException;
import com.homestore.security.user.User;
import com.homestore.util.UpdateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;
    private final AdDTOMapper adMapper;
    private final UpdateHelper updateHelper;

    @Override
    public List<AdResponse> getAds() {
        List<Ad> ads = adRepository.findAll();

        ads.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No ad found!"));

        return ads
                .stream()
                .map(adMapper)
                .collect(Collectors.toList());
    }

    @Override
    public AdResponse getAd(Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));

        return adMapper.apply(ad);
    }

    @Override
    public List<AdResponse> getAdsByCriteria(AdSearchCriteria adSearchCriteria){
        List<Ad> ads = adRepository.getAdsByCriteria(adSearchCriteria);

        ads.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No ad found!"));

        return ads
                .stream()
                .map(adMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public List<AdResponse> getMyAds(User user) {
        List<Ad> ads = adRepository.findAllByUserId(user.getId());

        ads.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No ad found!"));

        return ads
                .stream()
                .map(adMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public List<AdResponse> getMyAdsByStatus(User user, StatusEnum status) {
        List<Ad> ads = adRepository.findAllByUserIdAndStatus(user.getId(), status);

        ads.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No ad found!"));

        return ads
                .stream()
                .map(adMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void updateAd(User user, Long id, AdRequest request) {
        adRepository.findById(id)
                .map(ad -> {
                    if(!ad.getUserId().equals(user.getId())){
                        throw new UnauthorizedAccessException("You are not authorize to update this ad!");
                    }

                    String[] nulls = updateHelper.getNullPropertyNames(request);
                    BeanUtils.copyProperties(request, ad, nulls);

                    return adRepository.save(ad);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void deleteAd(User user, Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found"));

        if(!ad.getUserId().equals(user.getId())){
            throw new UnauthorizedAccessException("You are not authorize to delete this ad!");
        }

        adRepository.delete(ad);
    }
}