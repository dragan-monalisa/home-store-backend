package com.homestore.estate.ad;

import com.homestore.estate.ad.category.CategoryDTO;
import com.homestore.estate.property.PropertyDTO;
import com.homestore.security.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {
    private Long id;
    private Long realtorId;
    private Long price;
    private String status;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private UserDTO user;
    private PropertyDTO property;
    private CategoryDTO category;
}
