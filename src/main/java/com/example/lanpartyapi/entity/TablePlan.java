package com.example.lanpartyapi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "tableplan")
public class TablePlan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tableplan_id;



    @OneToMany(mappedBy = "tableplan", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public List<Segment> segments = new ArrayList<>();

    private String name;

    public void addSegment(Segment segment) {
        this.segments.add(segment);
    }

    public TablePlan(String name) {
        this.name = name;
    }



}
