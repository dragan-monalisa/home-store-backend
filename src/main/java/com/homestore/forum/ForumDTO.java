package com.homestore.forum;

import com.homestore.comment.CommentDTO;
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
    private Integer id;
    private ForumNameEnum name;
    private List<CommentDTO> comments;
}