package com.homestore.rental;

import com.homestore.ad.Ad;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Long price;

    @NotNull
    private Long propertyId;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "estate_ad_id"
    )
    private Ad ad;

    @NotNull
    private Long tenantId;

    @NotNull
    private Long contractId;

    @PrePersist
    public void onCreate() {
        this.startDate = LocalDate.now();
    }
}