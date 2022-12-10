package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.repository.LanUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthServiceTest {

    private LanUserService userService;
    private AuthService authService;
    private static LanUserRepository userRepositoryStatic;

    private static String user1Username;
    private static String user2Username;
    private static String passwordPreHash;
    private static String passwordPostHash;

    @BeforeAll
    public static void setup(@Autowired LanUserRepository userRepository) {
        userRepositoryStatic = userRepository;

        var salt = BCrypt.gensalt(12);

        passwordPreHash = "testing";
        passwordPostHash = BCrypt.hashpw(passwordPreHash, salt);

        LanUser user1 = new LanUser();
        user1.setUserType(LanUserType.USER);
        user1.setLanUserName("test_1");
        user1.setUserPassword(passwordPostHash);
        user1Username = user1.getLanUserName();

        userRepositoryStatic.save(user1);
    }

    @BeforeEach
    public void createService() {
        this.userService = new LanUserService(userRepositoryStatic);
        this.authService = new AuthService(userRepositoryStatic);
    }

    @Test
    public void signIn() {
        var lanUserRequest = new LanUserRequest();
        lanUserRequest.setUsername(user1Username);
        lanUserRequest.setPassword(passwordPreHash);

        assertDoesNotThrow(() -> this.authService.signIn(lanUserRequest));
    }
}