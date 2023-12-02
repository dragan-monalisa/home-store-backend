package com.homestore.security.token.jwt;

import java.util.List;

public interface JwtTokenService {
    void save(JwtToken token);

    List<JwtToken> findAllValidTokenByUser(Long id);
}