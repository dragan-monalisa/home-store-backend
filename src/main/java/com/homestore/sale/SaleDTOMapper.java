package com.homestore.sale;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class SaleDTOMapper implements Function<Sale, SaleResponse> {

    @Override
    public SaleResponse apply(Sale sale) {
        return new SaleResponse(
                sale.getId(),
                sale.getPrice(),
                sale.getProperty(),
                sale.getAd(),
                sale.getBuyer(),
                sale.getDate()
        );
    }
}