package com.example.lanpartyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class ReservationRequest {

    int chairData;
    int chairData2;
    int chairData3;
    int chairData4;

    public List<Integer> getIds(){
        List<Integer> chairIds = new ArrayList<>();
        if (chairData != 0){
            chairIds.add(this.chairData);
        }
        if (chairData2 != 0){
            chairIds.add(this.chairData2);
        }
        if (chairData3 != 0){
            chairIds.add(this.chairData3);
        }
        if (chairData4 != 0){
            chairIds.add(this.chairData4);
        }
        if(chairIds.size() == 0){
            throw new NullPointerException();
        }
        return chairIds;
    }
}
