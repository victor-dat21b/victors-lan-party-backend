package com.example.lanpartyapi.api;

import com.example.lanpartyapi.dto.DeskResponse;
import com.example.lanpartyapi.service.DeskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/desks")
public class DeskController {

    DeskService deskService;

    DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping("/")
    public Iterable<DeskResponse> getDesks() {
        return this.deskService.findAllDesks();
    }

    @GetMapping("/desk")
    public DeskResponse getOneDesk(@RequestParam int id){
        return this.deskService.getOneDesk(id);
    }

    //TODO desk from segment
    @GetMapping("/deskfromsegment")
    public List<DeskResponse> getDesksFromSegment(@RequestParam int id){//<-- Segment id

        return this.deskService.getDeskFromSegment(id);
    }


    @DeleteMapping("/deletedesk")
    public void deleteDesk(@RequestParam int id){
        this.deskService.deleteDesk(id);
    }

    @PostMapping("/createdesk")
    public void createDeskFromSegment(@RequestParam int id){
        this.deskService.createDeskFromSegment(id);
    }

}
