package com.homestore.sale;

import com.homestore.ad.Ad;
import com.homestore.ad.AdService;
import com.homestore.ad.StatusEnum;
import com.homestore.contract.Contract;
import com.homestore.contract.ContractService;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.security.user.User;
import com.homestore.security.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final AdService adService;
    private final UserService userService;
    private final ContractService contractService;
    private final SaleDTOMapper saleMapper;

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Override
    public List<SaleResponse> getSales(User realtor) {
        List<Sale> sales = saleRepository.findAllByRealtor(realtor.getId());

        if(sales.isEmpty()){
            throw new ResourceNotFoundException("No sale found!");
        }

        return sales
                .stream()
                .map(saleMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Override
    public SaleResponse getSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        return saleMapper.apply(sale);
    }

    @PreAuthorize("hasAnyAuthority('REALTOR')")
    @Transactional
    @Override
    public void saveSale(SaleRequest request){
        Ad ad = adService.findAdById(request.getAdId())
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found"));

        User buyer = userService.findUserById(request.getBuyerId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var contract = Contract.builder()
                .clauses(request.getClauses())
                .build();
        contract = contractService.saveContract(contract);

        var sale = Sale.builder()
                .price(request.getPrice())
                .propertyId(ad.getProperty().getId())
                .ad(ad)
                .buyerId(buyer.getId())
                .contractId(contract.getId())
                .build();

        ad.setStatus(StatusEnum.INACTIVE);
        saleRepository.save(sale);
    }
}