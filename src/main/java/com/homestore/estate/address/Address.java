package com.homestore.estate.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 56)
    @Column(nullable = false)
    private String county;

    @Size(max = 56)
    @Column(nullable = false)
    private String city;

    @Size(max = 56)
    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private Integer number;
}