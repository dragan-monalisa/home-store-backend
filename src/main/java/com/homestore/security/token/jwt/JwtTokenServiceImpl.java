package com.homestore.security.token.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService{

    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public void save(JwtToken token) {
        jwtTokenRepository.save(token);
    }

    @Override
    public List<JwtToken> findAllValidTokenByUser(Long id) {
        return jwtTokenRepository.findAllValidTokenByUser(id);
    }
}