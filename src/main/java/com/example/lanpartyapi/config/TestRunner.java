package com.example.lanpartyapi.config;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.service.LanUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private LanUserService lanUserService;

    public TestRunner(LanUserService lanUserService) {
        this.lanUserService = lanUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        LanUserRequest lanUserRequest = new LanUserRequest();
        lanUserRequest.setUsername("Splatman_dk");
        lanUserRequest.setPassword("test");

        this.lanUserService.create(lanUserRequest);
    }
}
