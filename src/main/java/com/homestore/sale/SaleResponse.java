package com.homestore.sale;

import com.homestore.ad.Ad;
import com.homestore.property.Property;
import com.homestore.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    private Long id;
    private Long price;
    private Property property;
    private Ad ad;
    private User buyer;
    private LocalDateTime date;
}