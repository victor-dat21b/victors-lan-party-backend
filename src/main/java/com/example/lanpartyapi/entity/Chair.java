package com.example.lanpartyapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chair" )
public class Chair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int chair_id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desk_id")
    private Desk desk;

    //OBS! If reservations_id is null it means a chair is not reserved.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;


    public void addDesk(Desk desk){
        this.desk = desk;
    }

    public int getDeskId() {
        return this.desk.getDesk_id();
    }
}
