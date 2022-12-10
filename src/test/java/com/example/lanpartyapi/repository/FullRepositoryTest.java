package com.example.lanpartyapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.DeskRepo;
import com.example.lanpartyapi.repository.SegmentRepo;
import com.example.lanpartyapi.repository.TablePlanRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class FullRepositoryTest {

    private static ChairRepo chairRepo;
    private static DeskRepo deskRepo;
    private static SegmentRepo segmentRepo;
    private static TablePlanRepo tablePlanRepo;


    @BeforeAll
    static void setupBeforeTests(
            @Autowired ChairRepo chairRepository,
            @Autowired DeskRepo deskRepository,
            @Autowired SegmentRepo segmentRepository,
            @Autowired TablePlanRepo tablePlanRepository
    ) {
        chairRepo = chairRepository;
        chairRepo.deleteAll();
        deskRepo = deskRepository;
        deskRepo.deleteAll();
        segmentRepo = segmentRepository;
        segmentRepo.deleteAll();
        tablePlanRepo = tablePlanRepository;
        tablePlanRepo.deleteAll();

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

        tablePlanRepo.save(tablePlan);
        segmentRepo.save(segment);
        segmentRepo.save(segment2);


        deskRepo.save(desk);
        deskRepo.save(desk2);
        deskRepo.save(desk3);
        deskRepo.save(desk4);

        chairRepo.save(chair);
        chairRepo.save(chair2);
        chairRepo.save(chair3);
        chairRepo.save(chair4);
        chairRepo.save(chair5);
        chairRepo.save(chair6);
        chairRepo.save(chair7);
        chairRepo.save(chair8);
    }

    @Test
    void testConnectionBetweenSegmentAndTablePlan() {
        List<TablePlan> tableList = tablePlanRepo.findAll();
        List<Segment> segmentList = segmentRepo.findAll();

        assertEquals(segmentList.get(0).getTableplan().getTableplan_id(), tableList.get(0).getTableplan_id());
    }

    @Test
    void testConnectionBetweenDeskAndSegment() {
        List<Segment> segmentList = segmentRepo.findAll();
        List<Desk> deskList = deskRepo.findAll();

        assertEquals(deskList.get(0).getSegment().getSegment_id(), segmentList.get(0).getSegment_id());
    }

    @Test
    void testConnectionBetweenChairAndDesk() {
        List<Desk> deskList = deskRepo.findAll();
        List<Chair> chairList = chairRepo.findAll();

        assertEquals(chairList.get(0).getDesk().getDesk_id(), deskList.get(0).getDesk_id());
    }

}
