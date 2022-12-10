package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.TablePlanResponse;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.service.TablePlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/tableplans")
public class TablePlanController {

    TablePlanService tablePlanService;

    TablePlanController(TablePlanService tablePlanService) {
        this.tablePlanService = tablePlanService;
    }

    @GetMapping("/")
    public List<TablePlanResponse> getTablePlans() {

        return this.tablePlanService.findAllTablePlans();

    }


    @PostMapping("/createtableplan")
    public void createTablePlan(@RequestParam String name) {
        this.tablePlanService.createTablePlan(name);
    }


    @DeleteMapping("/deletetableplan")
    public void deleteTablePlan(@RequestParam int id) {
        this.tablePlanService.deleteTablePlan(id);
    }

/*    @GetMapping("/tableplaninfo")
    public List<Segment> tablePlanInfo(@RequestParam int id){
        return tablePlanService.getTablePlanInfo(id);
    }*/



    @GetMapping("/alltableplansinfo")
    public List<TablePlan> allTablePlansInfo(){
        System.out.println(tablePlanService.getAllTablePlansInfo().get(0).getSegments());
        return tablePlanService.getAllTablePlansInfo();
    }





}
