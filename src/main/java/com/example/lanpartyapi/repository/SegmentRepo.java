package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.Chair;
import com.example.lanpartyapi.entity.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SegmentRepo extends JpaRepository<Segment, Integer> {

    //List<List<Chair>> findSegmentsByTableplan_id(int tableplan_id);
}
