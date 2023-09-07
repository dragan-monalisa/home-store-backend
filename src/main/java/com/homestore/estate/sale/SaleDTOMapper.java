package com.homestore.estate.sale;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class SaleDTOMapper implements Function<Sale, SaleDTO> {

    @Override
    public SaleDTO apply(Sale sale) {
        return new SaleDTO(
                sale.getAdId(),
                sale.getPrice(),
                sale.getPropertyId(),
                sale.getAdId(),
                sale.getBuyerId(),
                sale.getDate()
        );
    }
}
