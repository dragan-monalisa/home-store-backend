package com.homestore.property;

import com.homestore.ad.category.PropertyCategoryEnum;
import com.homestore.address.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE property SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Integer area;

    @Size(max = 1024)
    @NotBlank
    private String description;

    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private PropertyCategoryEnum category;

    private Integer floor;
    private Integer floorsNumber;
    private Integer buildYear;
    private Integer roomsNumber;
    private Integer bathroomsNumber;
    private String partitioning;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "address_id",
            nullable = false
    )
    private Address address;

    @PrePersist
    public void onCreate() {
        this.isActive = true;
    }
}