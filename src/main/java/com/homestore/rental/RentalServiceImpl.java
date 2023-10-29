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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RentalResponse> getRentals(User realtor) {
        List<Rental> rentals = rentalRepository.findAllByRealtor(realtor.getId());

        if(rentals.isEmpty()){
            throw new ResourceNotFoundException("No rental found!");
        }

        return rentals
                .stream()
                .map(rentalMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Override
    public RentalResponse getRental(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found"));

        return rentalMapper.apply(rental);
    }

    @Override
    public void saveRental(RentalRequest request) {
        Ad ad = adService.findAdById(request.getAdId())
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found"));

        User tenant = userService.findUserById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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