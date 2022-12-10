package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.SegmentResponse;
import com.example.lanpartyapi.service.SegmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/segments")
public class SegmentController {

    SegmentService segmentService;

    SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping("/")
    public List<SegmentResponse> getSegments() {
        return this.segmentService.findAllSegments();
    }


    @GetMapping("/segmentsfromtableplan")
    public List<SegmentResponse> getSegmentsFromTablePlan(@RequestParam int id) {
        return this.segmentService.getSegmentFromTablePlan(id);
    }

    @PostMapping("/createsegment")
    public void createSegmentsFromTablePlan(@RequestParam int id){
        this.segmentService.createSegmentFromTablePlan(id);
    }

    @DeleteMapping("/deletesegment")
    public void deleteDesk(@RequestParam int id){
        this.segmentService.deleteSegment(id);
    }


        /*
    @GetMapping(/"finddeskById")

    @PutMapping("/editdesk")

    @DeleteMapping("/deletedesk")
     */
}
