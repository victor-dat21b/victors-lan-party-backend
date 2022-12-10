package com.example.lanpartyapi.service;


import com.example.lanpartyapi.dto.DeskResponse;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.DeskRepo;
import com.example.lanpartyapi.repository.SegmentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeskService {

    DeskRepo deskRepo;
    SegmentRepo segmentRepo;

    public DeskService(DeskRepo deskRepo, SegmentRepo segmentRepo) {
        this.deskRepo = deskRepo;
        this.segmentRepo = segmentRepo;
    }

    public List<DeskResponse> findAllDesks() {
        List<Desk> desks = deskRepo.findAll();
        return desks.stream().map(desk -> new DeskResponse(desk)).collect(Collectors.toList());
    }

    public DeskResponse getOneDesk(int id) {
        Desk found = deskRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Desk not found"));

        return new DeskResponse(found);
    }

    public List<DeskResponse> getDeskFromSegment(int id) {
        Segment segment = segmentRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment not found"));
        List<Desk> desks = segment.getDesks();
        List<DeskResponse> found = new ArrayList<>();

        return desks.stream().map(desk -> new DeskResponse(desk)).collect(Collectors.toList());

 /*       for (int i = 0; i < desks.size(); i++) {
            found.add(new DeskResponse(desks.get(i)));
        }
        return found;*/
    }

    public void deleteDesk(int id) {
        Optional<Desk> desk = this.deskRepo.findById(id);
        if (desk.isPresent()){
            this.deskRepo.delete(desk.get());
        }
    }


    public void createDeskFromSegment(int id) {

        if (this.segmentRepo.findById(id).isPresent()){
            Segment segment = this.segmentRepo.findById(id).get();
            Desk desk = new Desk();
            segment.addDesk(desk);
            desk.setSegment(segment);
            this.segmentRepo.save(segment);
            this.deskRepo.save(desk);
        } else {
            throw new IllegalArgumentException("Tableplan not found through provided ID");
        }
    }

}
