package com.homestore.comment;

import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
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
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> postComment(@AuthenticationPrincipal User user,
                                              @RequestBody CommentRequest request){
        String response = commentService.postComment(user, request);

        if(ResponseEnum.ADDED.name().equals(response)){
            return ResponseEntity.ok("Comment successfully added!");
        }
        
        return new ResponseEntity<>("Please provide a valid comment!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal User user,
                                                @PathVariable Long id){
        String response = commentService.deleteComment(user, id);

        if(ResponseEnum.DELETED.name().equals(response)){
            return ResponseEntity.ok("Comment successfully deleted!");
        }
        else if(ResponseEnum.NOT_FOUND.name().equals(response)){
            return new ResponseEntity<>("Comment not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("You are not authorize to delete this comment!", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> editComment(@AuthenticationPrincipal User user,
                                                @PathVariable Long id,
                                                @RequestBody CommentRequest request){
        String response = commentService.editComment(user, id, request);

        if(ResponseEnum.UPDATED.name().equals(response)){
            return ResponseEntity.ok("Comment successfully updated!");
        }
        else if(ResponseEnum.UNAUTHORIZED.name().equals(response)){
            return new ResponseEntity<>("You are not authorize to edit this comment!", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Please provide a valid comment!", HttpStatus.BAD_REQUEST);
    }
}