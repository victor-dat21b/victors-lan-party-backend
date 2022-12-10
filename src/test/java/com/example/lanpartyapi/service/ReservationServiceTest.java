package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.ReservationRequest;
import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.LanUser;
import com.example.lanpartyapi.entity.LanUserType;
import com.example.lanpartyapi.entity.Reservation;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.LanUserRepository;
import com.example.lanpartyapi.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private LanUserRepository lanUserRepository;
    @Mock
    private ChairRepo chairRepo;
    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void setUpService() {
        this.reservationService = new ReservationService(
                this.reservationRepository,
                this.lanUserRepository,
                this.chairRepo
        );
    }

    @Test
    void create() {
        Mockito.when(this.lanUserRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(new LanUser()));

        var reservation = new ReservationRequest();
        reservation.setChairData(1);

        this.reservationService.create("test", reservation);

        Mockito.verify(this.reservationRepository,
                Mockito.times(1)).save(Mockito.any(Reservation.class));


    }

    @Test
    void createMany() {
        Chair chair = new Chair();
        chair.setChair_id(1);

        Mockito.when(this.lanUserRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(new LanUser()));

        Mockito.when(this.chairRepo.findAllById(Mockito.anyIterable()))
                .thenReturn(List.of(new Chair[]{chair}));

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setChairData(1);
        reservationRequest.setChairData2(0);
        reservationRequest.setChairData3(0);
        reservationRequest.setChairData4(0);

        this.reservationService.create("test", reservationRequest);

        Mockito.verify(this.reservationRepository,
                Mockito.times(1)).save(Mockito.any(Reservation.class));

        int size = reservationRequest.getIds().size();
        Mockito.verify(this.chairRepo, Mockito.times(size)).save(Mockito.any(Chair.class));

    }
}
