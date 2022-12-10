package com.example.lanpartyapi.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

public class JWTPayload {

    private final HashMap<String, String> payload;

    public JWTPayload(HashMap<String, String> payload) {
        this.payload = payload;
    }

    public String getUsername() {
        var username = this.payload.get("username");
        if (username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return username;
    }
}
