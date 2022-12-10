package com.example.lanpartyapi.dto;

import com.example.lanpartyapi.entity.Desk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeskResponse {

    int desk_id;
    int segment_id;


   public DeskResponse(Desk d){
        this.desk_id = d.getDesk_id();
        this.segment_id = d.getSegment().getSegment_id();
    }

}
