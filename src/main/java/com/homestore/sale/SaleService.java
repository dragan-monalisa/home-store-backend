package com.homestore.sale;

import com.homestore.security.user.User;
import java.util.List;

public interface SaleService {
    void saveSale(SaleRequest request);

    List<SaleResponse> getSales(User realtor);

    SaleResponse getSale(Long id);
}