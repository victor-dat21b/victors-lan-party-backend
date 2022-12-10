package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.JWTPayload;
import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.repository.LanUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    private LanUserRepository lanUserRepository;

    public AuthService(LanUserRepository lanUserRepository) {
        this.lanUserRepository = lanUserRepository;
    }

    public String signIn(LanUserRequest lanUserRequest) {
        String token;

        Optional<LanUser> lanUserOptional = this.lanUserRepository.findById(lanUserRequest.getUsername());
        if (lanUserOptional.isPresent()) {
            LanUser lanUser = lanUserOptional.get();

            if (BCrypt.checkpw(lanUserRequest.getPassword(), lanUser.getUserPassword())) {
                var jwtHandler = new JWTHandler();
                jwtHandler.sign(lanUser.getLanUserName());

                token = jwtHandler.getAccessToken();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return token;
    }

    public JWTPayload authorize(String bearerToken) {
        return this.authorize(bearerToken, LanUserType.USER);
    }

    public JWTPayload authorize(String bearerToken, LanUserType requiredLanUserType) {
        return this.authorization(bearerToken, requiredLanUserType);
    }

    //Todo: implement usertype authorization
    private JWTPayload authorization(String bearerToken, LanUserType requiredLanUserType) {
        bearerToken = bearerToken.replace("Bearer ", "");

        JWTHandler jwtHandler = new JWTHandler();

        return jwtHandler.decode(bearerToken);
    }
}
