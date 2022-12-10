package com.example.lanpartyapi.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.lanpartyapi.dto.ReservationRequest;
import com.example.lanpartyapi.entity.*;
import com.example.lanpartyapi.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ReservationServiceH2Test {

    private int foundId;
    private static ChairRepo chairRepo;
    private static DeskRepo deskRepo;
    private static SegmentRepo segmentRepo;
    private static TablePlanRepo tablePlanRepo;

    private static LanUserRepository lanUserRepo;

    private static ReservationRepository reservationRepo;

    private ReservationService reservationService;

    private static Chair chairStatic;
    private static Chair chairStatic2;
    private static Chair chairStatic3;
    private static Chair chairStatic4;
    private static Chair chairStatic5;
    private static Chair chairStatic6;
    private static Chair chairStatic7;
    private static Chair chairStatic8;


    @BeforeAll
    static void setupBeforeTests(
            @Autowired ChairRepo chairRepository,
            @Autowired DeskRepo deskRepository,
            @Autowired SegmentRepo segmentRepository,
            @Autowired TablePlanRepo tablePlanRepository,
            @Autowired LanUserRepository lanUserRepository,
            @Autowired ReservationRepository reservationRepository
    ) {
        chairRepo = chairRepository;
        deskRepo = deskRepository;
        segmentRepo = segmentRepository;
        tablePlanRepo = tablePlanRepository;
        lanUserRepo = lanUserRepository;
        reservationRepo = reservationRepository;

        LanUser user1 = new LanUser();
        user1.setUserType(LanUserType.USER);
        user1.setLanUserName("test_1");
        user1.setUserPassword("test");


        TablePlan tablePlan = new TablePlan();
        tablePlan.setName("Test tableplan");

        Segment segment = new Segment();
        Segment segment2 = new Segment();

        segment.setTableplan(tablePlan);
        segment2.setTableplan(tablePlan);

        tablePlan.addSegment(segment);
        tablePlan.addSegment(segment2);

        Desk desk = new Desk();
        Desk desk2 = new Desk();
        Desk desk3 = new Desk();
        Desk desk4 = new Desk();

        Chair chair = new Chair();
        Chair chair2 = new Chair();
        Chair chair3 = new Chair();
        Chair chair4 = new Chair();
        Chair chair5 = new Chair();
        Chair chair6 = new Chair();
        Chair chair7 = new Chair();
        Chair chair8 = new Chair();

        desk.addChair(chair);
        desk.addChair(chair2);
        desk2.addChair(chair3);
        desk2.addChair(chair4);
        desk3.addChair(chair5);
        desk3.addChair(chair6);
        desk4.addChair(chair7);
        desk4.addChair(chair8);

        chair.addDesk(desk);
        chair2.addDesk(desk);
        chair3.addDesk(desk2);
        chair4.addDesk(desk2);
        chair5.addDesk(desk3);
        chair6.addDesk(desk3);
        chair7.addDesk(desk4);
        chair8.addDesk(desk4);

        segment.addDesk(desk);
        segment.addDesk(desk2);
        segment2.addDesk(desk3);
        segment2.addDesk(desk4);

        desk.setSegment(segment);
        desk2.setSegment(segment);
        desk3.setSegment(segment2);
        desk4.setSegment(segment2);
        lanUserRepo.save(user1);

        tablePlanRepo.save(tablePlan);
        segmentRepo.save(segment);
        segmentRepo.save(segment2);


        deskRepo.save(desk);
        deskRepo.save(desk2);
        deskRepo.save(desk3);
        deskRepo.save(desk4);

        chairStatic = chairRepo.save(chair);
        chairStatic2 = chairRepo.save(chair2);
        chairStatic3 = chairRepo.save(chair3);
        chairStatic4 = chairRepo.save(chair4);
        chairStatic5 = chairRepo.save(chair5);
        chairStatic6 = chairRepo.save(chair6);
        chairStatic7 = chairRepo.save(chair7);
        chairStatic8 = chairRepo.save(chair8);
    }

    @BeforeEach
    public void createService(){
        this.reservationService = new ReservationService(reservationRepo, lanUserRepo, chairRepo);

    }

    @Test
    public void saveMoreThanOneReservationTest(){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setChairData(chairStatic.getChair_id());
        reservationRequest.setChairData2(chairStatic2.getChair_id());
        reservationRequest.setChairData3(chairStatic3.getChair_id());
        reservationRequest.setChairData4(0);

        this.reservationService.create("test_1", reservationRequest);

        List<Chair> foundChairs = chairRepo.findAll();

        assertNotNull(foundChairs.get(0).getReservation());//<---- Assert :-)
        assertNull(foundChairs.get(3).getReservation());


    }
}
