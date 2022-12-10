package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.dto.LanUserResponse;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.repository.LanUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LanUserService {

    private LanUserRepository userRepository;

    public LanUserService(LanUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(LanUserRequest userRequest) {
        var salt = BCrypt.gensalt(12);
        var passwordHash = BCrypt.hashpw(userRequest.getPassword(), salt);

        LanUser lanUser = new LanUser();
        lanUser.setUserType(LanUserType.USER);
        lanUser.setLanUserName(userRequest.getUsername());
        lanUser.setUserPassword(passwordHash);

        this.userRepository.save(lanUser);
    }

    public LanUserResponse findByUsername(String username) {
        var lanUserOptional = this.userRepository.findById(username);
        LanUserResponse lanUserResponse;
        if (lanUserOptional.isPresent()) {
            var lanUser = lanUserOptional.get();
            lanUserResponse = new LanUserResponse(lanUser.getLanUserName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return lanUserResponse;
    }


}
