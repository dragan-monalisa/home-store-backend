package com.homestore.forum;

import com.homestore.comment.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ForumNameEnum name;

    @OneToMany(
        mappedBy = "forum",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", name=" + name + '}';
    }
}