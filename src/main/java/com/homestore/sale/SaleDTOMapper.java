package com.homestore.sale;

import com.homestore.ad.AdDTOMapper;
import com.homestore.ad.AdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SaleDTOMapper implements Function<Sale, SaleResponse> {

    private final AdDTOMapper adMapper;

    @Override
    public SaleResponse apply(Sale sale) {
        AdResponse ad = adMapper.apply(sale.getAd());

        return new SaleResponse(
                sale.getId(),
                sale.getPrice(),
                sale.getPropertyId(),
                ad,
                sale.getBuyerId(),
                sale.getDate()
        );
    }
}