package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.*;
import com.example.lanpartyapi.service.AuthService;
import com.example.lanpartyapi.service.ChairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lanpartyapi.service.AuthService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/chairs")
public class ChairController {

    ChairService chairService;
    private AuthService authservice;

    ChairController(
            ChairService chairService,
            AuthService authservice
    ) {
        this.chairService = chairService;
        this.authservice = authservice;
    }

    @GetMapping("/")
    public Iterable<ChairResponse> getChairs() {
        return this.chairService.findAllChairs();
    }

    @GetMapping("/chairsFromDesk")
    public List<ChairResponse> getChairsFromDesk(@RequestParam int id) {
        return this.chairService.getChairsFromDesk(id);
    }

    @GetMapping("/segment/{segmentId}")
    public List<ChairResponse> findUnreservedBySegment(
            @PathVariable("segmentId") int segmentId,
            @RequestHeader("Authorization") String authHeader
    ) {
        this.authservice.authorize(authHeader);

        return this.chairService.findUnreservedBySegment(segmentId);
    }

    @DeleteMapping("/deletechair")
    public void deleteChair(@RequestParam int id) {
        this.chairService.deleteChair(id);
    }


    @PostMapping("/createchair")
    public void createChairFromDesk(@RequestParam int id) {
        this.chairService.createChairFromDesk(id);
    }

    @PutMapping("/updatechair/{chair_id}")
    public ResponseEntity<Boolean> updateChai00r(@RequestBody ChairRequest body, @PathVariable("chair_id") int id) {
        this.chairService.updateChair(body, id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
