package com.homestore.security.token.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService{

    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public void save(JwtToken token) {
        jwtTokenRepository.save(token);
    }
}