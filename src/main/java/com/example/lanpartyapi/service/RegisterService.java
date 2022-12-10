package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.LanUserRequest;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.repository.LanUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class RegisterService {

    LanUserRepository lanUserRepository;


    RegisterService(LanUserRepository lanUserRepository) {
        this.lanUserRepository = lanUserRepository;
    }

    public ResponseEntity<HashMap<String, String>> saveLanUser(LanUserRequest body) {

        Optional<LanUser> test = lanUserRepository.findById(body.getUsername());
        HashMap<String, String> responeHashmap = new HashMap<String, String>();
        if (test.isPresent()) {
            responeHashmap.put("response", "Brugernavn allerede taget");
            ResponseEntity<HashMap<String, String>> myresE = new ResponseEntity<>(responeHashmap, HttpStatus.FORBIDDEN);
            System.out.println(myresE);
            return myresE;
        } else {
            String name = body.getUsername();
            var salt = BCrypt.gensalt(12);
            var passwordHash = BCrypt.hashpw(body.getPassword(), salt);

            LanUser newUser = new LanUser();
            newUser.setLanUserName(name);
            newUser.setUserPassword(passwordHash);
            newUser.setUserType(LanUserType.USER);

            lanUserRepository.save(newUser);

            responeHashmap.put("response", "Du er nu oprette!");
            ResponseEntity<HashMap<String, String>> myresE2 = new ResponseEntity<>(responeHashmap, HttpStatus.OK);
            System.out.println(myresE2);

            return myresE2;
        }
    }
}
