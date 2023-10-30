package com.homestore.sale;

import com.homestore.security.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {
    void saveSale(SaleRequest request);

    Page<SaleResponse> getSales(User realtor, Pageable pageable);

    SaleResponse getSale(Long id);
}