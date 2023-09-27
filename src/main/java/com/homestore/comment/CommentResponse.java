package com.homestore.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homestore.security.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String text;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime postedAt;

    private boolean isVisible;
    private UserResponse user;
}