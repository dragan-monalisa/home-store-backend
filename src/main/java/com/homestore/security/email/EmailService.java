package com.homestore.security.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender{

    @Override
    public void send(String to, String email) {

    }
}