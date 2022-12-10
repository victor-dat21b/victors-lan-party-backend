package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChairRepo extends JpaRepository<Chair, Integer> {

    //Will find all the chairs that belong to a desk in a given segment
    @Query(
            value = "SELECT chair_id, desk.desk_id, reservation_id FROM chair INNER JOIN " +
                    "(SELECT desk.segment_id, desk.desk_id FROM desk INNER  JOIN segment s on " +
                    "desk.segment_id = s.segment_id WHERE s.segment_id = ?1) as desk " +
                    "on chair.desk_id = desk.desk_id",
            nativeQuery = true
    )
    List<Chair> findBySegment(int segmentId);
}
