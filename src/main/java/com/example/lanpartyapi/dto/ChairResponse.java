package com.example.lanpartyapi.dto;

import com.example.lanpartyapi.entity.Chair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ChairResponse {

    int chair_id;
    int desk_id;

    Integer reservation_id;

    public ChairResponse(Chair c) {
        this.chair_id = c.getChair_id();
        this.desk_id = c.getDesk().getDesk_id();
        //Checks if a reservation is present in Chair c. If it isn´t present it sets reservation_id to null;
        if (c.getReservation() == null) {
            this.reservation_id = null;
        } else {
            this.reservation_id = c.getReservation().getId();
        }
    }

}
