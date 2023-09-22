package com.homestore.comment;

import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> postComment(@AuthenticationPrincipal User user,
                                                       @RequestBody CommentRequest request) {
        return new ResponseEntity<>(commentService.postComment(user, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal User user,
                                                @PathVariable Long id) {
        commentService.deleteComment(user, id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponse> editComment(@AuthenticationPrincipal User user,
                                              @PathVariable Long id,
                                              @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.editComment(user, id, request));
    }
}