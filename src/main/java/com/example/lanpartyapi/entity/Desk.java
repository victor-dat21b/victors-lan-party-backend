package com.example.lanpartyapi.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "desk" )
public class Desk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int desk_id;
    @OneToMany(mappedBy = "desk", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<Chair> chairs = new ArrayList<>();


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segment_id")
    private Segment segment;

    public void addChair(Chair chair){
        this.chairs.add(chair);
    }

    public int getSegmentId() {
        return this.segment.getSegment_id();
    }
}
