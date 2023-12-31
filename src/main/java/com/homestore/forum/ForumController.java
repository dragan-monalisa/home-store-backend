package com.homestore.forum;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forums")
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public ResponseEntity<List<ForumResponse>> getForums(){
        return ResponseEntity.ok(forumService.getForums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumResponse> getForum(@PathVariable Long id){
        return ResponseEntity.ok(forumService.getForum(id));
    }

    @PostMapping
    public ResponseEntity<String> saveForum(@RequestBody ForumRequest request){
        forumService.saveForum(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ForumResponse> editForum(@PathVariable Long id,
                                                   @RequestBody ForumRequest request){
        forumService.editForum(id, request);

        return ResponseEntity.ok().build();
    }
}