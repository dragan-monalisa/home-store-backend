package com.homestore.comment;

import com.homestore.forum.Forum;
import com.homestore.security.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 640)
    @Column(nullable = false)
    private String text;

    private LocalDateTime postedAt;
    private boolean isVisible;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "forum_id"
    )
    private Forum forum;

    @PrePersist
    public void onCreate() {
        this.postedAt = LocalDateTime.now();
        this.isVisible = true;
    }
}