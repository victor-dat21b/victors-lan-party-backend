package com.example.lanpartyapi.service;


import com.example.lanpartyapi.dto.SegmentResponse;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.SegmentRepo;
import com.example.lanpartyapi.repository.TablePlanRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SegmentService {

    SegmentRepo segmentRepo;
    TablePlanRepo tablePlanRepo;

    public SegmentService(SegmentRepo segmentRepo, TablePlanRepo tablePlanRepo) {
        this.segmentRepo = segmentRepo;
        this.tablePlanRepo = tablePlanRepo;
    }

    public List<SegmentResponse> findAllSegments() {
        List<Segment> segments = segmentRepo.findAll();
        return segments.stream().map(segment -> new SegmentResponse(segment)).collect(Collectors.toList());
    }

    public List<SegmentResponse> getSegmentFromTablePlan(int id) {
        TablePlan tablePlan = this.tablePlanRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TablePlan not found"));
        List<Segment> segments = tablePlan.getSegments();
        List<SegmentResponse> found = new ArrayList<>();

        return segments.stream().map(segment -> new SegmentResponse(segment)).collect(Collectors.toList());
    }

    public void createSegmentFromTablePlan(int id) {

        if (this.tablePlanRepo.findById(id).isPresent()){
            TablePlan tablePlan = this.tablePlanRepo.findById(id).get();
            Segment segment = new Segment();
            tablePlan.addSegment(segment);
            segment.setTableplan(tablePlan);
            this.tablePlanRepo.save(tablePlan);
            this.segmentRepo.save(segment);
        } else {
            throw new IllegalArgumentException("Tableplan not found through provided ID");
        }
    }

    public void deleteSegment(int id) {
        Optional<Segment> segment = this.segmentRepo.findById(id);
        if (segment.isPresent()){
            this.segmentRepo.delete(segment.get());
        }
    }

    public List<Segment> getSegments() {
        return this.segmentRepo.findAll();
    }
}
