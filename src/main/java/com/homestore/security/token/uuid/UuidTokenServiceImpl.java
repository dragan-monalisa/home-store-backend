package com.homestore.security.token.uuid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UuidTokenServiceImpl implements UuidTokenService{

    private final UuidTokenRepository uuidTokenRepository;

    @Override
    public void save(UuidToken token) {
        uuidTokenRepository.save(token);
    }
}