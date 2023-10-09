package com.homestore.ad;

import com.homestore.ad.category.AdCategoryEnum;
import com.homestore.property.Property;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "property_ad")
@SQLDelete(sql = "UPDATE property_ad SET status = 'INACTIVE' WHERE id = ?")
@Where(clause = "status = 'active'")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Size(max = 48)
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Enumerated(EnumType.STRING)
    private AdCategoryEnum category;

    @Column(nullable = false)
    private Long realtorId;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "property_id"
    )
    private Property property;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = StatusEnum.PENDING;
    }
}