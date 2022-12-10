package com.example.lanpartyapi.repository;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.SegmentRepo;
import com.example.lanpartyapi.repository.TablePlanRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TablePlanRepoTest {

    private static TablePlanRepo tablePlanRepo;
    private static SegmentRepo segmentRepo;

    @BeforeAll
    static void setUpTablePlan(@Autowired TablePlanRepo tablePlanRepository, @Autowired SegmentRepo segmentRepository){
        tablePlanRepo = tablePlanRepository;
        tablePlanRepo.deleteAll();
        segmentRepo = segmentRepository;
        segmentRepo.deleteAll();

        Segment newSegment = new Segment();
        Segment newSegment2 = new Segment();
        Segment newSegment3 = new Segment();

        List<Segment> segments = new ArrayList<>();
        segments.add(newSegment);
        segments.add(newSegment2);
        segments.add(newSegment3);

        TablePlan myTablePlan1 = new TablePlan();
        myTablePlan1.setName("Bordplan1");
        myTablePlan1.setTableplan_id(1);
        myTablePlan1.setSegments(segments);

        tablePlanRepo.save(myTablePlan1);

    }


    @Test
    void findAll(){
        List<TablePlan> tablePlans = tablePlanRepo.findAll();

        assertEquals(1, tablePlans.size());
    }

    @Test
    void findTablePlanByName(){
        TablePlan tablePlans = tablePlanRepo.findByName("Bordplan1").get();

        assertEquals("Bordplan1", tablePlans.getName());
    }

    @Test
    void findTablePlaneByID(){
        //List<TablePlan> tablePlans = tablePlanRepo.findById(1);

        //assertEquals(1, tablePlans.size());
    }


}
