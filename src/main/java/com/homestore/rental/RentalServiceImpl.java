package com.homestore.rental;

import com.homestore.ad.Ad;
import com.homestore.ad.AdService;
import com.homestore.ad.StatusEnum;
import com.homestore.contract.Contract;
import com.homestore.contract.ContractService;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.security.user.User;
import com.homestore.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{

    private final RentalRepository rentalRepository;
    private final RentalDTOMapper rentalMapper;
    private final AdService adService;
    private final UserService userService;
    private final ContractService contractService;

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Override
    public Page<RentalResponse> getRentals(User realtor, Pageable pageable) {
        Page<Rental> page = rentalRepository.findAllByRealtor(realtor.getId(), pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No rental found!");
        }

        return page.map(rentalMapper);
    }

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Override
    public RentalResponse getRental(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found!"));

        return rentalMapper.apply(rental);
    }

    @Override
    public void saveRental(RentalRequest request) {
        Ad ad = adService.findAdById(request.getAdId())
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));

        User tenant = userService.findUserById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        var contract = Contract.builder()
                .clauses(request.getClauses())
                .build();
        contract = contractService.saveContract(contract);

        var rental = Rental.builder()
                .endDate(request.getEndDate())
                .price(request.getPrice())
                .propertyId(ad.getProperty().getId())
                .ad(ad)
                .tenantId(tenant.getId())
                .contractId(contract.getId())
                .build();

        ad.setStatus(StatusEnum.INACTIVE);
        adService.save(ad);
        rentalRepository.save(rental);
    }
}