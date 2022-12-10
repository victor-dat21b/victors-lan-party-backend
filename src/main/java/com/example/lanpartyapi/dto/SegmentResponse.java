package com.example.lanpartyapi.dto;

import com.example.lanpartyapi.entity.Segment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SegmentResponse {

    int segment_id;
    int tableplan_id;



    public SegmentResponse(Segment s){
        this.segment_id = s.getSegment_id();
        this.tableplan_id = s.getTableplan().getTableplan_id();
    }



}
