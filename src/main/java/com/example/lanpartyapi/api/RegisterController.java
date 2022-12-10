package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegisterController {

    RegisterService registerService;

    RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping()
    public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody LanUserRequest body){
        return this.registerService.saveLanUser(body);
    }

}
