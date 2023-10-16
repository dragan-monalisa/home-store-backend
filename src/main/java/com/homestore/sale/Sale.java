package com.homestore.sale;

import com.homestore.ad.Ad;
import com.homestore.property.Property;
import com.homestore.security.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long price;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "property_id"
    )
    private Property property;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "estate_ad_id"
    )
    private Ad ad;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "buyer_id"
    )
    private User buyer;

    @Column(nullable = false)
    private Long contractId;

    private LocalDateTime date;

    @PrePersist
    public void onCreate() {
        this.date = LocalDateTime.now();
    }
}