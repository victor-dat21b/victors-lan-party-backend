package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.ReservationRequest;
import com.example.lanpartyapi.entity.Reservation;
import com.example.lanpartyapi.service.AuthService;

import com.example.lanpartyapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationController {

    private final AuthService authService;
    private final ReservationService reservationService;

    public ReservationController(
            AuthService authService,
            ReservationService reservationService
    ) {
        this.authService = authService;
        this.reservationService = reservationService;
    }

    //Uses old method for authentication
    @GetMapping
    public List<Reservation> getUserReservation(@RequestHeader("Authorization") String bearerToken
    ) {
       var payload = this.authService.authorize(bearerToken);

        return this.reservationService.findByLanUserName(payload.getUsername());
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> create(
           @RequestHeader("Authorization") String auth,
            @RequestBody ReservationRequest reservationRequest
    ) {
       var payload = this.authService.authorize(auth);

       this.reservationService.create(payload.getUsername(), reservationRequest);



        return new ResponseEntity<>(
                new HashMap<>(Map.of("message", "Successfully created new reservation")),
                HttpStatus.OK
        );
    }
}
