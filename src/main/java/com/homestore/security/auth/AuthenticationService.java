package com.homestore.security.auth;

import com.homestore.security.auth.request.AuthenticationRequest;
import com.homestore.security.auth.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}