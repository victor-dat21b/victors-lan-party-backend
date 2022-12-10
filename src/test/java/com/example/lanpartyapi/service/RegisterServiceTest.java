package com.example.lanpartyapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.repository.LanUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@DataJpaTest
public class RegisterServiceTest {


    static LanUserRepository lanUserRepositoryStatic;

    RegisterService registerServiceStatic;

    static String passWord = "123";
    static String userName = "test";

    static LanUserType role = LanUserType.USER;

    @BeforeAll
    public static void setUp(@Autowired LanUserRepository lanUserRepository) {
        lanUserRepositoryStatic = lanUserRepository;
        LanUser user = new LanUser();
        user.setLanUserName(userName);
        user.setUserPassword(passWord);
        user.setUserType(role);
        lanUserRepositoryStatic.save(user);
    }

    @BeforeEach
    public void beforeEach(){
        this.registerServiceStatic = new RegisterService(lanUserRepositoryStatic);
    }
    @Test
    public void saveLanUserTest() {
        LanUserRequest lanUserRequest = new LanUserRequest();
        lanUserRequest.setPassword("123");
        lanUserRequest.setUsername(userName);

        ResponseEntity<HashMap<String, String>> response =
                registerServiceStatic.saveLanUser(lanUserRequest);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());


    }
}
