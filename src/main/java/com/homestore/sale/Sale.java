package com.homestore.sale;

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
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Long buyerId;

    @NotNull
    private Long contractId;

    private LocalDateTime date;

    @PrePersist
    public void onCreate() {
        this.date = LocalDateTime.now();
    }
}