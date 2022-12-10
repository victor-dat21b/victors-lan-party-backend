package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.ReservationRequest;
import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.Reservation;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.LanUserRepository;
import com.example.lanpartyapi.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final LanUserRepository lanUserRepository;
    private final ChairRepo chairRepo;


    public ReservationService(
            ReservationRepository reservationRepository,
            LanUserRepository lanUserRepository,
            ChairRepo chairRepo
    ) {
        this.reservationRepository = reservationRepository;
        this.lanUserRepository = lanUserRepository;
        this.chairRepo = chairRepo;
    }

    public List<Reservation> findByLanUserName(String username) {
        return this.reservationRepository.findByLanUser_LanUserName(username);
    }

    public void create(String lanUserName, ReservationRequest reservationRequest) {
        List<Integer> chairIds;
        try {
            chairIds = reservationRequest.getIds();

        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<Chair> chairList;
        var lanUserOptional = this.lanUserRepository.findById(lanUserName);


        chairList = this.chairRepo.findAllById(chairIds);
        var lanUser = lanUserOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        for (Chair chair : chairList) {
            if (chair.getReservation() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        var reservation = new Reservation();
        reservation.setLanUser(lanUser);
        Reservation reservation1 = this.reservationRepository.save(reservation);

        for (Chair chair: chairList){
            chair.setReservation(reservation1);
            this.chairRepo.save(chair);
        }

    }

}
