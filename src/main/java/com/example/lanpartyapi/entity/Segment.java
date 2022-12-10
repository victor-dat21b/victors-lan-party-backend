package com.example.lanpartyapi.entity;

import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "segment" )
public class Segment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int segment_id;



    @OneToMany(mappedBy = "segment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Desk> desks = new ArrayList<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "tableplan_id")
    @ManyToOne(fetch = FetchType.LAZY)
    TablePlan tableplan;

    public void addDesk(Desk desk){
        this.desks.add(desk);
    }



}
