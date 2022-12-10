package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/signIn")
    public ResponseEntity<HashMap<String, String>> signIn(@RequestBody LanUserRequest lanUserRequest){
        var token = this.authService.signIn(lanUserRequest);

        return new ResponseEntity<>(new HashMap<>(Map.of("accessToken", token)), HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<HashMap<String, String>> authorize(@RequestBody HashMap<String, String> payload) {
        this.authService.authorize(payload.get("accessToken"));

        return new ResponseEntity<>(new HashMap<>(Map.of("message", "Successful authorization")), HttpStatus.OK);
    }
}
