package com.homestore.security.auth;

import com.homestore.exception.ResourceConflictException;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnsuccessfulOperationException;
import com.homestore.security.auth.request.AuthenticationRequest;
import com.homestore.security.auth.request.RegisterRequest;
import com.homestore.security.config.JwtService;
import com.homestore.security.token.jwt.JwtToken;
import com.homestore.security.token.jwt.JwtTokenService;
import com.homestore.security.token.uuid.UuidToken;
import com.homestore.security.token.uuid.UuidTokenService;
import com.homestore.user.User;
import com.homestore.user.UserRoleEnum;
import com.homestore.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UuidTokenService uuidTokenService;

    @Value("${domain}")
    private String domain;

    @Value("${uuid.token.expiration}")
    private int expireTime;

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
        sendConfirmationEmail(user.getEmail());
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userService.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void sendConfirmationEmail(String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if(user.getConfirmedAt() != null){
            throw new UnsuccessfulOperationException("User already confirmed!");
        }

        if (user.getUuidTokens() == null) {
            user.setUuidTokens(new ArrayList<>());
        }

        if (!user.getUuidTokens().isEmpty()) {
            throw new UnsuccessfulOperationException("Mail already sent!");
        }

        final String token = UUID.randomUUID().toString();
        final String link = domain + "/api/v1/auth/confirm-account?confirmationToken=" + token;

        var buildToken = UuidToken.builder()
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(expireTime))
                .user(user)
                .build();

        uuidTokenService.save(buildToken);
        //
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = JwtToken.builder()
                .user(user)
                .token(jwtToken)
                .build();

        jwtTokenService.save(token);
    }
}
