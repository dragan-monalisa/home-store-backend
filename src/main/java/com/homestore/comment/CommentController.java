package com.homestore.comment;

import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/forum")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        List<CommentDTO> comments = commentService.getAllComments();

        if(!comments.isEmpty()){
            return ResponseEntity.ok(comments);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> addReply(@AuthenticationPrincipal User user,
                                           @RequestBody CommentRequest request){
        String response = commentService.addComment(user, request);

        if(response.equals(ResponseEnum.ADDED.name())){
            return ResponseEntity.ok("Comment successfully added!");
        }
        
        return new ResponseEntity<>("Users are required to have participated in a rental or sale activity, to be able to leave a comment!", HttpStatus.BAD_REQUEST);
    }
}