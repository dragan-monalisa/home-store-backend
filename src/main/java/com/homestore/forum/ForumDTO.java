package com.homestore.forum;

import com.homestore.comment.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForumDTO {
    private ForumNameEnum name;
    private List<CommentResponse> comments;
}