package com.example.lanpartyapi.repository;

import com.example.lanpartyapi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByLanUser_LanUserName(String username);
}
