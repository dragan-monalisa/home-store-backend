package com.homestore.security.token.jwt;

import com.homestore.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.Where;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_revoked = false AND is_expired = false")
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 256)
    @Column(unique = true)
    private String token;

    private boolean isRevoked;
    private boolean isExpired;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    @PrePersist
    protected void onCreate() {
        this.isRevoked = false;
        this.isExpired = false;
    }
}