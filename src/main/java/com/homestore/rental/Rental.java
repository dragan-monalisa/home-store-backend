package com.homestore.rental;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long propertyId;

    @Column(name = "estate_ad_id", nullable = false)
    private Long adId;

    @Column(nullable = false)
    private Long tenantId;

    @Column(nullable = false)
    private Long contractId;

    @PrePersist
    public void onCreate() {
        this.startDate = LocalDateTime.now();
    }
}