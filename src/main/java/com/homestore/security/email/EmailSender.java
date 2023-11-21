package com.homestore.security.email;

public interface EmailSender {

    void send(String to, String email);
}
