package com.homestore.ad;

import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnauthorizedAccessException;
import com.homestore.property.Property;
import com.homestore.property.PropertyService;
import com.homestore.user.User;
import com.homestore.user.UserRoleEnum;
import com.homestore.user.UserService;
import com.homestore.util.UpdateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService{

    private final AdRepository adRepository;
    private final AdDTOMapper adMapper;
    private final UpdateHelper updateHelper;
    private final PropertyService propertyService;
    private final UserService userService;

    @Override
    public Page<AdResponse> getAds(Pageable pageable) {
        Page<Ad> page = adRepository.findAllAds(pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No ad found!");
        }

        return page.map(adMapper);
    }

    @Override
    public AdResponse getAd(Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));

        return adMapper.apply(ad);
    }

    @Override
    public Page<AdResponse> getAdsByFilters(SearchCriteria searchCriteria, Pageable pageable){
        Page<Ad> page = adRepository.getAdsByFilters(searchCriteria, pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No ad found!");
        }

        return page.map(adMapper);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'REALTOR')")
    @Override
    public Page<AdResponse> getMyAdsByStatus(User user, StatusEnum status, Pageable pageable) {
        Page<Ad> page = new PageImpl<>(Collections.emptyList());

        if(user.getRole().equals(UserRoleEnum.USER)){
            page = adRepository.findAllByUserIdAndStatus(user.getId(), status, pageable);
        }
        else if(user.getRole().equals(UserRoleEnum.REALTOR)){
            page = adRepository.findAllByRealtorIdAndStatus(user.getId(), status, pageable);
        }

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No ad found!");
        }

        return page.map(adMapper);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'REALTOR')")
    @Override
    public void updateAd(User user, Long id, AdRequest request) {
        adRepository.findById(id)
                .map(ad -> {
                    if(!ad.getUserId().equals(user.getId()) || !ad.getRealtorId().equals(user.getId())){
                        throw new UnauthorizedAccessException("You are not authorize to update this ad!");
                    }

                    String[] nulls = updateHelper.getNullPropertyNames(request);
                    BeanUtils.copyProperties(request, ad, nulls);

                    return adRepository.save(ad);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));
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

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void saveAd(User user, AdRequest request) {
        Property property = propertyService.findById(request.getPropertyId())
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));

        User realtor = userService.findUserById(request.getRealtorId())
                .orElseThrow(() -> new ResourceNotFoundException("Realtor not found"));

        var ad = Ad.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .category(request.getCategory())
                .userId(user.getId())
                .realtorId(realtor.getId())
                .property(property)
                .build();

        adRepository.save(ad);
    }

    @Override
    public void save(Ad ad) {
        adRepository.save(ad);
    }

    @Override
    public Optional<Ad> findAdById(Long id) {
        return adRepository.findById(id);
    }
}