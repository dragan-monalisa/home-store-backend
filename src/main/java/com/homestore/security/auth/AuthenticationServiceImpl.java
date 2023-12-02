package com.homestore.security.auth;

import com.homestore.exception.ResourceConflictException;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnsuccessfulOperationException;
import com.homestore.security.auth.request.AuthenticationRequest;
import com.homestore.security.auth.request.RegisterRequest;
import com.homestore.security.config.JwtService;
import com.homestore.security.token.jwt.JwtToken;
import com.homestore.security.token.jwt.JwtTokenService;
import com.homestore.user.User;
import com.homestore.user.UserRoleEnum;
import com.homestore.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public void register(RegisterRequest request) {
        String email = request.getEmail();

        if (userService.userExists(email)) {
            throw new ResourceConflictException("Email already taken!");
        } else if (!userService.isEmailValid(email)) {
            throw new UnsuccessfulOperationException("Invalid mail format!");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(email)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRoleEnum.USER)
                .build();

        user = userService.save(user);
        var jwtToken = jwtService.generateToken(user);

        saveUserToken(user, jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (user.getIsEnabled()) {
            String token = jwtService.generateToken(user);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            revokeAllUserTokens(user);
            saveUserToken(user, token);

            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        }

        throw new ResourceNotFoundException("User not found!");
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = jwtTokenService.findAllValidTokenByUser(user.getId());

        if(validUserTokens.isEmpty()){
            return;
        }

        validUserTokens.forEach(jwtToken -> {
            jwtToken.setExpired(true);
            jwtToken.setRevoked(true);

            jwtTokenService.save(jwtToken);
        });
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = JwtToken.builder()
                .user(user)
                .token(jwtToken)
                .build();

        jwtTokenService.save(token);
    }
}
