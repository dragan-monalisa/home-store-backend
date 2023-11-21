package com.homestore.user;

import com.homestore.comment.Comment;
import com.homestore.favorite.Favorite;
import com.homestore.security.token.jwt.JwtToken;
import com.homestore.security.token.uuid.UuidToken;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 32)
    @NotBlank
    private String firstName;

    @Size(max = 32)
    @NotBlank
    private String lastName;

    @Size(max = 64)
    @NotBlank
    @Column(
            unique = true
    )
    private String email;

    @Size(max = 64)
    @NotBlank
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    private Boolean isLocked;
    private Boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime confirmedAt;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<JwtToken> jwtTokens;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<UuidToken> uuidTokens;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Comment> comments;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Favorite> favorites;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role + '}';
    }

    @PrePersist
    protected void onCreate() {
        this.isLocked = false;
        this.isEnabled = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}