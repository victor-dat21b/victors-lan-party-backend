package com.example.lanpartyapi.config;

import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.SegmentRepo;
import com.example.lanpartyapi.repository.TablePlanRepo;
import com.example.lanpartyapi.repository.DeskRepo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class CommonTest implements ApplicationRunner {

    ChairRepo chairRepo;
    SegmentRepo segmentRepo;
    DeskRepo deskRepo;
    TablePlanRepo tablePlanRepo;


    public CommonTest(ChairRepo chairRepo, SegmentRepo segmentRepo, DeskRepo deskRepo, TablePlanRepo tablePlanRepo) {

        this.chairRepo = chairRepo;
        this.segmentRepo = segmentRepo;
        this.deskRepo = deskRepo;
        this.tablePlanRepo = tablePlanRepo;

    }




    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
