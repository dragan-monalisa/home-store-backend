package com.homestore.user;

import com.homestore.security.auth.request.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PatchMapping("/change-password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal User user,
                                                 @RequestBody PasswordRequest request){
        userService.changePassword(user, request);

        return ResponseEntity.ok().build();
    }
}