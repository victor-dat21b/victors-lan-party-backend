package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.LanUserResponse;
import com.example.lanpartyapi.service.JWTHandler;
import com.example.lanpartyapi.service.LanUserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class LanUserController {


    private final LanUserService lanUserService;

    public LanUserController(LanUserService lanUserService) {
        this.lanUserService = lanUserService;
    }

    @PostMapping("/findUser")
    public LanUserResponse findUserUsername(@RequestBody HashMap<String, String> payload) {

        JWTHandler jwtHandler = new JWTHandler();
        var jwtPayload = jwtHandler.decode(payload.get("accessToken"));

        return lanUserService.findByUsername(jwtPayload.getUsername());

    }

}


