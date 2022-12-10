package com.example.lanpartyapi.service;

import com.example.lanpartyapi.dto.ChairResponse;
import com.example.lanpartyapi.dto.ReservationRequest;
import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.entity.Reservation;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.DeskRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ChairServiceTest {

    @Mock
    private ChairRepo chairRepo;
    @Mock
    private DeskRepo deskRepo;
    private ChairService chairService;

    @BeforeEach
    public void setupService() {
        this.chairService = new ChairService(this.chairRepo, deskRepo);

    }
    @Test
    void findUnreservedBySegment() {
        Reservation reservation = new Reservation();
        ReservationRequest reservationRequest = new ReservationRequest();
        reservation.setId(1);


        var desk = new Desk();

        var chair1 = new Chair();//true
        chair1.setDesk(desk);
        chair1.setReservation(reservation);

        var chair2 = new Chair();//true
        chair2.setDesk(desk);
        chair2.setReservation(reservation);

        var chair3 = new Chair();//false
        chair3.setDesk(desk);
        chair3.setReservation(null);

        List<Chair> chairs = List.of(chair1, chair2, chair3);

        Mockito.when(this.chairRepo.findBySegment(Mockito.anyInt())).thenReturn(chairs);

        var segmentId = 1;

        var unreservedChairsBySegment = this.chairService.findUnreservedBySegment(segmentId);
        Mockito.verify(this.chairRepo, Mockito.times(1)).findBySegment(Mockito.eq(segmentId));

        assertNotEquals(0, unreservedChairsBySegment.size());
        unreservedChairsBySegment.forEach(chairResponse -> assertNull(chairResponse.getReservation_id()));
    }
}
