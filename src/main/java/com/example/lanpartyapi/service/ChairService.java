package com.example.lanpartyapi.service;


import com.example.lanpartyapi.dto.ChairRequest;
import com.example.lanpartyapi.dto.ChairResponse;
import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Desk;
import com.example.lanpartyapi.entity.Segment;
import com.example.lanpartyapi.entity.TablePlan;
import com.example.lanpartyapi.repository.ChairRepo;
import com.example.lanpartyapi.repository.DeskRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChairService {

    ChairRepo chairRepo;

    DeskRepo deskRepo;

    public ChairService(ChairRepo chairRepo, DeskRepo deskRepo) {
        this.chairRepo = chairRepo;
        this.deskRepo = deskRepo;
    }

    public List<ChairResponse> findAllChairs() {
        List<Chair> plans = this.chairRepo.findAll();
        return plans.stream().map(chair -> new ChairResponse(chair)).collect(Collectors.toList());
    }

    public List<ChairResponse> getChairsFromDesk(int id) {
        Desk desk = this.deskRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment not found"));
        List<Chair> chairs = desk.getChairs();
        List<ChairResponse> found = new ArrayList<>();

        return chairs.stream().map(chair -> new ChairResponse(chair)).collect(Collectors.toList());
        //for (int i = 0; i < chairs.size(); i++) {
        //    found.add(new ChairResponse(chairs.get(i)));
    }

    public List<ChairResponse> findBySegment(int segmentId) {
        var chairs = this.chairRepo.findBySegment(segmentId);

        return chairs.stream().map(ChairResponse::new).toList();
    }

    public List<ChairResponse> findUnreservedBySegment(int segmentId) {
        var chairs = this.findBySegment(segmentId);

        return chairs.stream().filter(chairResponse -> chairResponse.getReservation_id() == null).toList();
    }

    public void deleteChair(int id) {
        Optional<Chair> chair = this.chairRepo.findById(id);
        if (chair.isPresent()) {
            this.chairRepo.delete(chair.get());
        }
    }

    public void createChairFromDesk(int id) {

        if (this.deskRepo.findById(id).isPresent()) {
            Desk desk = this.deskRepo.findById(id).get();
            if (desk.getChairs().size() != 2) {
                Chair chair = new Chair();
                desk.addChair(chair);
                chair.setDesk(desk);
                this.deskRepo.save(desk);
                this.chairRepo.save(chair);
            } else {
                throw new IllegalArgumentException("Two chairs are already connected to this desk");
            }

        } else {
            throw new IllegalArgumentException("Tableplan not found through provided ID");
        }
    }


    //SKal nok laves om.
    public void updateChair(ChairRequest body, int id) {

        Chair chair = chairRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chair not found"));
        if (body.getId() != chair.getChair_id()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change this chair");
        }
        chair.setChair_id(body.getId());
        //updateChairState(chair);
        //chair.setDesk(body.getDesk());

        chairRepo.save(chair);
    }

}
