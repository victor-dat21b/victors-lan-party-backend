package com.example.lanpartyapi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Reservation {

    //OBS! If reservations_id is 0 it means a chair is not reserved.
    // We assume there isn´t an actual reservation_id with the number 0.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "lan_user_name")
    @ManyToOne(fetch = FetchType.LAZY)
    private LanUser lanUser;

     public String getOwnerUsername() {
        String username = "";
        if (this.lanUser != null) {
            username = this.lanUser.getLanUserName();
        }

        return username;
    }
}
