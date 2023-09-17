package com.homestore.forum;

import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/forum")
public class ForumController {

    private final ForumService forumService;

    @GetMapping
    public ResponseEntity<ForumDTO> getForumByName(@RequestParam("forumName") String name){
        ForumDTO forum = forumService.getForumByName(name);

        if(forum != null){
            return ResponseEntity.ok(forum);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveForum(@RequestBody ForumRequest request){
        String response = forumService.saveForum(request);

        if(ResponseEnum.SAVED.name().equals(response)){
            return ResponseEntity.ok("Forum successfully added!");
        }

        return new ResponseEntity<>("Forum name already exist!", HttpStatus.CONFLICT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> editForumName(@PathVariable Integer id,
                                                @RequestBody ForumRequest request){
        String response = forumService.editForumName(id, request);

        if(ResponseEnum.UPDATED.name().equals(response)){
            return ResponseEntity.ok("Forum successfully updated!");
        }

        return new ResponseEntity<>("Please provide a valid forum data!", HttpStatus.BAD_REQUEST);
    }
}