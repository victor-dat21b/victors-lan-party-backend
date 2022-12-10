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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LanUserServiceTest {

    private LanUserService userService;
    private static LanUserRepository userRepositoryStatic;

    private static String user1Username;
    private static String user2Username;

    @BeforeAll
    public static void setup(@Autowired LanUserRepository userRepository) {
        userRepositoryStatic = userRepository;
        //userRepositoryStatic.deleteAll();

        LanUser user1 = new LanUser();
        user1.setUserType(LanUserType.USER);
        user1.setLanUserName("test_1");
        user1.setUserPassword("test");
        user1Username = user1.getLanUserName();

        LanUser user2 = new LanUser();
        user2.setUserType(LanUserType.ADMIN);
        user2.setLanUserName("test_2");
        user2.setUserPassword("test");
        user2Username = user2.getLanUserName();

        userRepositoryStatic.save(user1);
        userRepositoryStatic.save(user2);
    }

    @BeforeEach
    public void createService() {
        this.userService = new LanUserService(userRepositoryStatic);
    }

    @Test
    void create() {
        var username = "test 3";

        var lanUser = new LanUserRequest();
        lanUser.setUsername(username);
        lanUser.setPassword("test");

        this.userService.create(lanUser);

        var lanUserFound = this.userService.findByUsername(username);
        assertEquals(lanUserFound.getUsername(), lanUser.getUsername());
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> this.userService.findByUsername(user1Username));
    }
}